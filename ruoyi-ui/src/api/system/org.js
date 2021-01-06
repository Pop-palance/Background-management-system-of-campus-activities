import request from '@/utils/request'
 
// 查询组织列表
export function listOrg(query) {
  return request({
    url: '/system/org/list',
    method: 'get',
    params: query
  })
}

// 查询组织详细
export function getOrg(oid) {
  return request({
    url: '/system/org/' + oid,
    method: 'get'
  })
}

// 新增组织
export function addOrg(data) {
  return request({
    url: '/system/org',
    method: 'post',
    data: data
  })
}

// 修改组织
export function updateOrg(data) {
  return request({
    url: '/system/org',
    method: 'put',
    data: data
  })
}

// 删除组织
export function delOrg(oid) {
  return request({
    url: '/system/org/' + oid,
    method: 'delete'
  })
}

// 导出组织
export function exportOrg(query) {
  return request({
    url: '/system/org/export',
    method: 'get',
    params: query
  })
}