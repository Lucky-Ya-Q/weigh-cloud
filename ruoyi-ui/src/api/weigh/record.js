import request from '@/utils/request'

// 查询称重记录列表
export function listRecord(query) {
  return request({
    url: '/weigh/record/list',
    method: 'get',
    params: query
  })
}

// 查询称重记录详细
export function getRecord(id) {
  return request({
    url: '/weigh/record/' + id,
    method: 'get'
  })
}

// 新增称重记录
export function addRecord(data) {
  return request({
    url: '/weigh/record',
    method: 'post',
    data: data
  })
}

// 修改称重记录
export function updateRecord(data) {
  return request({
    url: '/weigh/record',
    method: 'put',
    data: data
  })
}

// 删除称重记录
export function delRecord(id) {
  return request({
    url: '/weigh/record/' + id,
    method: 'delete'
  })
}
