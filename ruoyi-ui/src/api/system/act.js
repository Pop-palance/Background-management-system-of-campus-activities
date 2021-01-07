import request from '@/utils/request'

// 查询学生活动成绩列表
export function listAct(query) {
  return request({
    url: '/system/act/list',
    method: 'get',
    params: query
  })
}

// 查询学生活动成绩详细
export function getAct(sno) {
  return request({
    url: '/system/act/' + sno,
    method: 'get'
  })
}

// 新增学生活动成绩
export function addAct(data) {
  return request({
    url: '/system/act',
    method: 'post',
    data: data
  })
}

// 修改学生活动成绩
export function updateAct(data) {
  return request({
    url: '/system/act',
    method: 'put',
    data: data
  })
}

// 删除学生活动成绩
export function delAct(sno) {
  return request({
    url: '/system/act/' + sno,
    method: 'delete'
  })
}

// 导出学生活动成绩
export function exportAct(query) {
  return request({
    url: '/system/act/export',
    method: 'get',
    params: query
  })
}