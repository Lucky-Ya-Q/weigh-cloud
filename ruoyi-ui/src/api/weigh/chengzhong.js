import request from '@/utils/request'

// 查询称重列表
export function listChengzhong(query) {
  return request({
    url: '/weigh/chengzhong/list',
    method: 'get',
    params: query
  })
}

// 查询称重详细
export function getChengzhong(id) {
  return request({
    url: '/weigh/chengzhong/' + id,
    method: 'get'
  })
}

// 新增称重
export function addChengzhong(data) {
  return request({
    url: '/weigh/chengzhong',
    method: 'post',
    data: data
  })
}

// 修改称重
export function updateChengzhong(data) {
  return request({
    url: '/weigh/chengzhong',
    method: 'put',
    data: data
  })
}

// 删除称重
export function delChengzhong(id) {
  return request({
    url: '/weigh/chengzhong/' + id,
    method: 'delete'
  })
}
