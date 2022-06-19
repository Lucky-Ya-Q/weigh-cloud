import request from '@/utils/request'

// 查询翻译列表
export function listFanyi(query) {
  return request({
    url: '/weigh/fanyi/list',
    method: 'get',
    params: query
  })
}

// 查询翻译详细
export function getFanyi(id) {
  return request({
    url: '/weigh/fanyi/' + id,
    method: 'get'
  })
}

// 新增翻译
export function addFanyi(data) {
  return request({
    url: '/weigh/fanyi',
    method: 'post',
    data: data
  })
}

// 修改翻译
export function updateFanyi(data) {
  return request({
    url: '/weigh/fanyi',
    method: 'put',
    data: data
  })
}

// 删除翻译
export function delFanyi(id) {
  return request({
    url: '/weigh/fanyi/' + id,
    method: 'delete'
  })
}
