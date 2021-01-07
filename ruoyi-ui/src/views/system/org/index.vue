<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="密码" prop="opwd">
        <el-input
          v-model="queryParams.opwd"
          placeholder="请输入密码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="oname">
        <el-input
          v-model="queryParams.oname"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="父部门id" prop="parentid">
        <el-input
          v-model="queryParams.parentid"
          placeholder="请输入父部门id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="祖级列表" prop="ancestors">
        <el-input
          v-model="queryParams.ancestors"
          placeholder="请输入祖级列表"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createtime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="手机号" prop="onum">
        <el-input
          v-model="queryParams.onum"
          placeholder="请输入手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="oemail">
        <el-input
          v-model="queryParams.oemail"
          placeholder="请输入邮箱"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="ostatus">
        <el-select v-model="queryParams.ostatus" placeholder="请选择状态" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="职务" prop="ojob">
        <el-input
          v-model="queryParams.ojob"
          placeholder="请输入职务"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="角色" prop="orole">
        <el-input
          v-model="queryParams.orole"
          placeholder="请输入角色"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:org:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:org:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:org:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:org:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orgList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="组织id" align="center" prop="oid" />
      <el-table-column label="密码" align="center" prop="opwd" />
      <el-table-column label="名称" align="center" prop="oname" />
      <el-table-column label="父部门id" align="center" prop="parentid" />
      <el-table-column label="祖级列表" align="center" prop="ancestors" />
      <el-table-column label="创建时间" align="center" prop="createtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center" prop="onum" />
      <el-table-column label="邮箱" align="center" prop="oemail" />
      <el-table-column label="状态" align="center" prop="ostatus" />
      <el-table-column label="职务" align="center" prop="ojob" />
      <el-table-column label="角色" align="center" prop="orole" />
      <el-table-column label="备注" align="center" prop="onote" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:org:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:org:remove']"
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

    <!-- 添加或修改组织对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="密码" prop="opwd">
          <el-input v-model="form.opwd" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="名称" prop="oname">
          <el-input v-model="form.oname" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="父部门id" prop="parentid">
          <el-input v-model="form.parentid" placeholder="请输入父部门id" />
        </el-form-item>
        <el-form-item label="手机号" prop="onum">
          <el-input v-model="form.onum" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="邮箱" prop="oemail">
          <el-input v-model="form.oemail" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.ostatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="职务" prop="ojob">
          <el-input v-model="form.ojob" placeholder="请输入职务" />
        </el-form-item>
        <el-form-item label="角色" prop="orole">
          <el-input v-model="form.orole" placeholder="请输入角色" />
        </el-form-item>
        <el-form-item label="备注" prop="onote">
          <el-input v-model="form.onote" type="textarea" placeholder="请输入内容" />
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
import { listOrg, getOrg, delOrg, addOrg, updateOrg, exportOrg } from "@/api/system/org";

export default {
  name: "Org",
  components: {
  },
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
      // 组织表格数据
      orgList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        opwd: null,
        oname: null,
        parentid: null,
        ancestors: null,
        createtime: null,
        onum: null,
        oemail: null,
        ostatus: null,
        ojob: null,
        orole: null,
        onote: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        opwd: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
        oname: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        onum: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询组织列表 */
    getList() {
      this.loading = true;
      listOrg(this.queryParams).then(response => {
        this.orgList = response.rows;
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
        oid: null,
        opwd: null,
        oname: null,
        parentid: null,
        ancestors: null,
        createtime: null,
        onum: null,
        oemail: null,
        ostatus: "0",
        ojob: null,
        orole: null,
        onote: null
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.oid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加组织";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const oid = row.oid || this.ids
      getOrg(oid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改组织";
      });
      console.log(this.form);
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.oid != null) {
            updateOrg(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrg(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const oids = row.oid || this.ids;
      this.$confirm('是否确认删除组织编号为"' + oids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOrg(oids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有组织数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOrg(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
