<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学号" prop="sno">
        <el-input
          v-model="queryParams.sno"
          placeholder="请输入学号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动号" prop="aid">
        <el-input
          v-model="queryParams.aid"
          placeholder="请输入活动号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动分数" prop="grade">
        <el-input
          v-model="queryParams.grade"
          placeholder="请输入活动分数"
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
          v-hasPermi="['system:act:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:act:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:act:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:act:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="actList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学号" align="center" prop="sno" />
      <el-table-column label="活动id" align="center" prop="aid" />
      <el-table-column label="活动分数" align="center" prop="grade" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:act:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:act:remove']"
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

    <!-- 添加或修改学生活动成绩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学号" prop="sno">
          <el-input v-model="form.sno" placeholder="请输入学号" />
        </el-form-item>
        <el-form-item label="活动号" prop="aid">
          <el-input v-model="form.aid" placeholder="请输入活动号" />
        </el-form-item>
        <el-form-item label="活动分数" prop="grade">
          <el-input v-model="form.grade" placeholder="请输入活动分数" />
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
import { listAct, getAct, delAct, addAct, updateAct, exportAct } from "@/api/system/act";

export default {
  name: "Act",
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
      // 学生活动成绩表格数据
      actList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        grade: null,
        sno:null,
        aid:null
      },
      currentParams:{
        sno:null,
        aid:null,
        grade:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getCurrentParams();
  },
  methods: {
    
    /** 查询学生活动成绩列表 */
    getList() {
      this.loading = true;
      listAct(this.queryParams).then(response => {
        this.actList = response.rows;
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
        sno: null,
        aid: null,
        grade: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
      console.log(this.queryParams);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.sno)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生活动成绩";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sno = row.sno || this.ids
      this.currentParams.sno=row.sno;
      getAct(sno).then(response => {
        this.form = response.data;
        this.form.sno=sno;
        this.open = true;
        this.title = "修改学生活动成绩";
      });
    },
    /** 提交按钮 */
    submitForm() {
      console.log("skdfjdj");
      this.$refs["form"].validate(valid => {
        // if (valid) {
        //   if (this.currentParams.sno == null) {
        //     console.log("修改");
        //     console.log(this.currentParams);
        //     updateAct(this.form).then(response => {
        //       this.msgSuccess("修改成功");
        //       this.open = false;
        //       this.getList();
        //     });
        //   } else {
        //     addAct(this.form).then(response => {
        //       this.msgSuccess("新增成功");
        //       this.open = false;
        //       this.getList();
        //     });
        //   }
        // }
          if (valid) {
          
            addAct(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const snos = row.sno || this.ids;
      this.$confirm('是否确认删除学生活动成绩编号为"' + snos + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAct(snos);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有学生活动成绩数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAct(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
