<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="货物名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入货物名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="货物类型" prop="goodsType">
        <el-input
          v-model="queryParams.goodsType"
          placeholder="请输入货物类型"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车牌号" prop="plateNumber">
        <el-input
          v-model="queryParams.plateNumber"
          placeholder="请输入车牌号"
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
          v-hasPermi="['weigh:record:add']"
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
          v-hasPermi="['weigh:record:edit']"
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
          v-hasPermi="['weigh:record:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['weigh:record:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="货物名称" align="center" prop="goodsName" />
      <el-table-column label="货物类型" align="center" prop="goodsType" />
      <el-table-column label="车牌号" align="center" prop="plateNumber" />
      <el-table-column label="称重时间1" align="center" prop="weighTime1" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.weighTime1, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="称重时间2" align="center" prop="weighTime2" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.weighTime2, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发货单位" align="center" prop="fahuo" />
      <el-table-column label="运输单位" align="center" prop="yunshu" />
      <el-table-column label="收货单位" align="center" prop="shouhuo" />
      <el-table-column label="照片" align="center" prop="photos" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photos" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column v-for="field in fieldList" :key="field.id" :label="field.name" align="center" :prop="field.code" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="150">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['weigh:record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['weigh:record:remove']"
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

    <!-- 添加或修改称重记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="货物名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入货物名称" />
        </el-form-item>
        <el-form-item label="货物类型" prop="goodsType">
          <el-input v-model="form.goodsType" placeholder="请输入货物类型" />
        </el-form-item>
        <el-form-item label="车牌号" prop="plateNumber">
          <el-input v-model="form.plateNumber" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="称重时间1" prop="weighTime1">
          <el-date-picker clearable size="small"
            v-model="form.weighTime1"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择称重时间1">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="称重时间2" prop="weighTime2">
          <el-date-picker clearable size="small"
            v-model="form.weighTime2"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择称重时间2">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发货单位" prop="fahuo">
          <el-input v-model="form.fahuo" placeholder="请输入发货单位" />
        </el-form-item>
        <el-form-item label="运输单位" prop="yunshu">
          <el-input v-model="form.yunshu" placeholder="请输入运输单位" />
        </el-form-item>
        <el-form-item label="收货单位" prop="shouhuo">
          <el-input v-model="form.shouhuo" placeholder="请输入收货单位" />
        </el-form-item>
        <el-form-item label="照片">
          <image-upload v-model="form.photos"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item v-for="(field,index) in fieldList" :key="field.id" :label="field.name" :prop="field.code">
          <el-input v-model="form.wcExtendDataList[index].value"  :placeholder="'请输入' + field.name" />
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
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/weigh/record";
import {listAllField} from "@/api/weigh/field";

export default {
  name: "Record",
  data() {
    return {
      // 扩展字段表格数据
      fieldList: [],
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
      // 称重记录表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsName: null,
        goodsType: null,
        plateNumber: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getListAll();
  },
  methods: {
    /** 查询所有称重记录列表 */
    getListAll() {
      this.loading = true;
      listAllField().then(response => {
        this.fieldList = response.data;
        this.reset()
        this.getList();
      });
    },
    /** 查询称重记录列表 */
    getList() {
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        for (let i = 0; i < this.recordList.length; i++) {
          let wcExtendDataList = this.recordList[i].wcExtendDataList;
          for (let j = 0; j < wcExtendDataList.length; j++) {
            this.recordList[i][wcExtendDataList[j].code] = wcExtendDataList[j].value
          }
        }
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
        goodsName: null,
        goodsType: null,
        plateNumber: null,
        weighTime1: null,
        weighTime2: null,
        fahuo: null,
        yunshu: null,
        shouhuo: null,
        photos: null,
        userId: null,
        deptId: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        wcExtendDataList: []
      };
      for (let i = 0; i < this.fieldList.length; i++) {
        this.form.wcExtendDataList.push({code:this.fieldList[i].code,value:null})
      }
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
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
      this.title = "添加称重记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRecord(id).then( async response => {
        this.form = response.data;
        let realWcExtendDataList = JSON.parse(JSON.stringify(this.form.wcExtendDataList));
        const {data} = await listAllField(row.deptId)
        this.fieldList = data;
        for (let i = 0; i < this.fieldList.length; i++) {
          // 返回所有满足条件的元素
          let filter = realWcExtendDataList.filter(value => value.code === this.fieldList[i].code)
          let value = filter.length === 1 ? filter[0].value : null
          this.$set(this.form.wcExtendDataList, i, {code: this.fieldList[i].code, value})
        }
        this.open = true;
        this.title = "修改称重记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除称重记录编号为"' + ids + '"的数据项？').then(function() {
        return delRecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('weigh/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
