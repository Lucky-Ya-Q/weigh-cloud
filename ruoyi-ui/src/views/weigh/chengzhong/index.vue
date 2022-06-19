<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车牌号" prop="chepai">
        <el-input
          v-model="queryParams.chepai"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="进厂时间">
        <el-date-picker
          v-model="daterangeTime1"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="出厂时间">
        <el-date-picker
          v-model="daterangeTime2"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="发货单位" prop="fahuo">
        <el-input
          v-model="queryParams.fahuo"
          placeholder="请输入发货单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收货单位" prop="shouhuo">
        <el-input
          v-model="queryParams.shouhuo"
          placeholder="请输入收货单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="运输单位" prop="yunshu">
        <el-input
          v-model="queryParams.yunshu"
          placeholder="请输入运输单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['weigh:chengzhong:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['weigh:chengzhong:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['weigh:chengzhong:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['weigh:chengzhong:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="chengzhongList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="车牌号" align="center" prop="chepai" />
      <el-table-column label="进厂时间" align="center" prop="time1" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.time1, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="出厂时间" align="center" prop="time2" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.time2, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="毛重" align="center" prop="mao" />
      <el-table-column label="皮重" align="center" prop="pi" />
      <el-table-column label="净重" align="center" prop="jing" />
      <el-table-column label="发货单位" align="center" prop="fahuo" />
      <el-table-column label="收货单位" align="center" prop="shouhuo" />
      <el-table-column label="运输单位" align="center" prop="yunshu" />
      <el-table-column :label="fanyi.photo1 ? fanyi.photo1 : '照片1'" align="center" prop="photo1" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo1" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column :label="fanyi.photo2 ? fanyi.photo2 : '照片2'" align="center" prop="photo2" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo2" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column :label="fanyi.photo3 ? fanyi.photo3 : '照片3'" align="center" prop="photo3" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo3" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column :label="fanyi.photo4 ? fanyi.photo4 : '照片4'" align="center" prop="photo4" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo4" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column :label="fanyi.photo5 ? fanyi.photo5 : '照片5'" align="center" prop="photo5" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo5" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column :label="fanyi.photo6 ? fanyi.photo6 : '照片6'" align="center" prop="photo6" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo6" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column :label="fanyi.photo7 ? fanyi.photo7 : '照片7'" align="center" prop="photo7" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo7" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column :label="fanyi.photo8 ? fanyi.photo8 : '照片8'" align="center" prop="photo8" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo8" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column :label="fanyi.remark1 ? fanyi.remark1 : '备注1'" align="center" prop="remark1" />
      <el-table-column :label="fanyi.remark2 ? fanyi.remark2 : '备注2'" align="center" prop="remark2" />
      <el-table-column :label="fanyi.remark3 ? fanyi.remark3 : '备注3'" align="center" prop="remark3" />
      <el-table-column :label="fanyi.remark4 ? fanyi.remark4 : '备注4'" align="center" prop="remark4" />
      <el-table-column :label="fanyi.remark5 ? fanyi.remark5 : '备注5'" align="center" prop="remark5" />
      <el-table-column :label="fanyi.remark6 ? fanyi.remark6 : '备注6'" align="center" prop="remark6" />
      <el-table-column :label="fanyi.remark7 ? fanyi.remark7 : '备注7'" align="center" prop="remark7" />
      <el-table-column :label="fanyi.remark8 ? fanyi.remark8 : '备注8'" align="center" prop="remark8" />
      <el-table-column :label="fanyi.remark9 ? fanyi.remark9 : '备注9'" align="center" prop="remark9" />
      <el-table-column :label="fanyi.remark10 ? fanyi.remark10 : '备注10'" align="center" prop="remark10" />
      <el-table-column :label="fanyi.remark11 ? fanyi.remark11 : '备注11'" align="center" prop="remark11" />
      <el-table-column :label="fanyi.remark12 ? fanyi.remark12 : '备注12'" align="center" prop="remark12" />
      <el-table-column :label="fanyi.remark13 ? fanyi.remark13 : '备注13'" align="center" prop="remark13" />
      <el-table-column :label="fanyi.remark14 ? fanyi.remark14 : '备注14'" align="center" prop="remark14" />
      <el-table-column :label="fanyi.remark15 ? fanyi.remark15 : '备注15'" align="center" prop="remark15" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['weigh:chengzhong:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['weigh:chengzhong:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改称重对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车牌号" prop="chepai">
          <el-input v-model="form.chepai" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="进厂时间" prop="time1">
          <el-date-picker clearable size="small"
                          v-model="form.time1"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择进厂时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="出厂时间" prop="time2">
          <el-date-picker clearable size="small"
                          v-model="form.time2"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择出厂时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="毛重" prop="mao">
          <el-input v-model="form.mao" placeholder="请输入毛重" />
        </el-form-item>
        <el-form-item label="皮重" prop="pi">
          <el-input v-model="form.pi" placeholder="请输入皮重" />
        </el-form-item>
        <el-form-item label="净重" prop="jing">
          <el-input v-model="form.jing" placeholder="请输入净重" />
        </el-form-item>
        <el-form-item label="发货单位" prop="fahuo">
          <el-input v-model="form.fahuo" placeholder="请输入发货单位" />
        </el-form-item>
        <el-form-item label="收货单位" prop="shouhuo">
          <el-input v-model="form.shouhuo" placeholder="请输入收货单位" />
        </el-form-item>
        <el-form-item label="运输单位" prop="yunshu">
          <el-input v-model="form.yunshu" placeholder="请输入运输单位" />
        </el-form-item>
        <el-form-item :label="fanyi.photo1 ? fanyi.photo1 : '照片1'">
          <image-upload v-model="form.photo1"/>
        </el-form-item>
        <el-form-item :label="fanyi.photo2 ? fanyi.photo2 : '照片2'">
          <image-upload v-model="form.photo2"/>
        </el-form-item>
        <el-form-item :label="fanyi.photo3 ? fanyi.photo3 : '照片3'">
          <image-upload v-model="form.photo3"/>
        </el-form-item>
        <el-form-item :label="fanyi.photo4 ? fanyi.photo4 : '照片4'">
          <image-upload v-model="form.photo4"/>
        </el-form-item>
        <el-form-item :label="fanyi.photo5 ? fanyi.photo5 : '照片5'">
          <image-upload v-model="form.photo5"/>
        </el-form-item>
        <el-form-item :label="fanyi.photo6 ? fanyi.photo6 : '照片6'">
          <image-upload v-model="form.photo6"/>
        </el-form-item>
        <el-form-item :label="fanyi.photo7 ? fanyi.photo7 : '照片7'">
          <image-upload v-model="form.photo7"/>
        </el-form-item>
        <el-form-item :label="fanyi.photo8 ? fanyi.photo8 : '照片8'">
          <image-upload v-model="form.photo8"/>
        </el-form-item>
        <el-form-item :label="fanyi.remark1 ? fanyi.remark1 : '备注1'" prop="remark1">
          <el-input v-model="form.remark1" :placeholder="fanyi.remark1 ? '请输入' + fanyi.remark1 : '请输入备注1'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark2 ? fanyi.remark2 : '备注2'" prop="remark2">
          <el-input v-model="form.remark2" :placeholder="fanyi.remark2 ? '请输入' + fanyi.remark2 : '请输入备注2'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark3 ? fanyi.remark3 : '备注3'" prop="remark3">
          <el-input v-model="form.remark3" :placeholder="fanyi.remark3 ? '请输入' + fanyi.remark3 : '请输入备注3'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark4 ? fanyi.remark4 : '备注4'" prop="remark4">
          <el-input v-model="form.remark4" :placeholder="fanyi.remark4 ? '请输入' + fanyi.remark4 : '请输入备注4'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark5 ? fanyi.remark5 : '备注5'" prop="remark5">
          <el-input v-model="form.remark5" :placeholder="fanyi.remark5 ? '请输入' + fanyi.remark5 : '请输入备注5'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark6 ? fanyi.remark6 : '备注6'" prop="remark6">
          <el-input v-model="form.remark6" :placeholder="fanyi.remark6 ? '请输入' + fanyi.remark6 : '请输入备注6'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark7 ? fanyi.remark7 : '备注7'" prop="remark7">
          <el-input v-model="form.remark7" :placeholder="fanyi.remark7 ? '请输入' + fanyi.remark7 : '请输入备注7'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark8 ? fanyi.remark8 : '备注8'" prop="remark8">
          <el-input v-model="form.remark8" :placeholder="fanyi.remark8 ? '请输入' + fanyi.remark8 : '请输入备注8'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark9 ? fanyi.remark9 : '备注9'" prop="remark9">
          <el-input v-model="form.remark9" :placeholder="fanyi.remark9 ? '请输入' + fanyi.remark9 : '请输入备注9'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark10 ? fanyi.remark10 : '备注10'" prop="remark10">
          <el-input v-model="form.remark10" :placeholder="fanyi.remark10 ? '请输入' + fanyi.remark10 : '请输入备注10'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark11 ? fanyi.remark11 : '备注11'" prop="remark11">
          <el-input v-model="form.remark11" :placeholder="fanyi.remark11 ? '请输入' + fanyi.remark11 : '请输入备注11'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark12 ? fanyi.remark12 : '备注12'" prop="remark12">
          <el-input v-model="form.remark12" :placeholder="fanyi.remark12 ? '请输入' + fanyi.remark12 : '请输入备注12'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark13 ? fanyi.remark13 : '备注13'" prop="remark13">
          <el-input v-model="form.remark13" :placeholder="fanyi.remark13 ? '请输入' + fanyi.remark13 : '请输入备注13'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark14 ? fanyi.remark14 : '备注14'" prop="remark14">
          <el-input v-model="form.remark14" :placeholder="fanyi.remark14 ? '请输入' + fanyi.remark14 : '请输入备注14'" />
        </el-form-item>
        <el-form-item :label="fanyi.remark15 ? fanyi.remark15 : '备注15'" prop="remark15">
          <el-input v-model="form.remark15" :placeholder="fanyi.remark15 ? '请输入' + fanyi.remark15 : '请输入备注15'" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listChengzhong, getChengzhong, delChengzhong, addChengzhong, updateChengzhong } from "@/api/weigh/chengzhong";
import {listFanyi} from "@/api/weigh/fanyi";

export default {
  name: "Chengzhong",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 称重表格数据
      chengzhongList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeTime1: [],
      // 备注时间范围
      daterangeTime2: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        chepai: null,
        time1: null,
        time2: null,
        fahuo: null,
        shouhuo: null,
        yunshu: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // 翻译表格数据
      fanyi: {},
    };
  },
  created() {
    this.getFanyiList();
    this.getList();
  },
  methods: {
    /** 查询翻译列表 */
    getFanyiList() {
      listFanyi().then(response => {
        const rows = response.rows;
        for (const row of rows) {
          this.fanyi[row.fieldValue] = row.fieldLabel
        }
        console.log(this.fanyi)
      });
    },
    /** 查询称重列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeTime1 && '' != this.daterangeTime1) {
        this.queryParams.params["beginTime1"] = this.daterangeTime1[0];
        this.queryParams.params["endTime1"] = this.daterangeTime1[1];
      }
      if (null != this.daterangeTime2 && '' != this.daterangeTime2) {
        this.queryParams.params["beginTime2"] = this.daterangeTime2[0];
        this.queryParams.params["endTime2"] = this.daterangeTime2[1];
      }
      listChengzhong(this.queryParams).then(response => {
        this.chengzhongList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        chepai: null,
        time1: null,
        time2: null,
        mao: null,
        pi: null,
        jing: null,
        fahuo: null,
        shouhuo: null,
        yunshu: null,
        photo1: null,
        photo2: null,
        photo3: null,
        photo4: null,
        photo5: null,
        photo6: null,
        photo7: null,
        photo8: null,
        remark1: null,
        remark2: null,
        remark3: null,
        remark4: null,
        remark5: null,
        remark6: null,
        remark7: null,
        remark8: null,
        remark9: null,
        remark10: null,
        remark11: null,
        remark12: null,
        remark13: null,
        remark14: null,
        remark15: null,
        userId: null,
        deptId: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeTime1 = [];
      this.daterangeTime2 = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加称重";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getChengzhong(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改称重";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateChengzhong(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addChengzhong(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除称重编号为"' + ids + '"的数据项？').then(function() {
        return delChengzhong(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('weigh/chengzhong/export', {
        ...this.queryParams
      }, `chengzhong_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
