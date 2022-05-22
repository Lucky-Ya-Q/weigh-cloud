import request from '@/utils/request'

// 查询扩展字段列表
export function listField(query) {
  return request({
    url: '/weigh/field/list',
    method: 'get',
    params: query
  })
}

// 查询扩展字段详细
export function getField(id) {
  return request({
    url: '/weigh/field/' + id,
    method: 'get'
  })
}

// 新增扩展字段
export function addField(data) {
  return request({
    url: '/weigh/field',
    method: 'post',
    data: data
  })
}

// 修改扩展字段
export function updateField(data) {
  return request({
    url: '/weigh/field',
    method: 'put',
    data: data
  })
}

// 删除扩展字段
export function delField(id) {
  return request({
    url: '/weigh/field/' + id,
    method: 'delete'
  })
}

// 查询所有扩展字段列表
export function listAllField(query) {
  return request({
    url: '/weigh/field/listAll',
    method: 'get',
    params: {deptId: query}
  })
}
