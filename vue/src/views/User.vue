<template>
<div>
    <div style="padding: 10px 0;text-align: right" >
        <el-input style="width: 500px" placeholder="请输入名称/邮箱/电话/地址" suffix-icon="el-icon-search" v-model="integration"></el-input>
        <!--                    <el-input style="width: 500px" placeholder="请输入名称/邮箱/电话/地址" suffix-icon="el-icon-search" v-model="username"></el-input>-->
        <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
        <el-button  type="warning" @click="reset">重置</el-button>
    </div>
    <div style="padding: 10px 0">

        <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
        <el-popconfirm
                class="ml-5"
                confirm-button-text='好的'
                cancel-button-text='我在想想'
                icon="el-icon-info"
                icon-color="red"
                title="您确定删除吗？"
                @confirm="deleteBatch"
        >
            <el-button type="danger" slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
        </el-popconfirm>
        <el-upload
                action="http://localhost:8888/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">导入<i class="el-icon-bottom"></i></el-button>
        </el-upload>
        <el-button type="primary" class="ml-5" @click="exp">导出<i class="el-icon-top"></i></el-button>
    </div>
    <!--反馈数据库表格-->
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="id" label="ID" width="80"></el-table-column>
        <el-table-column prop="username" label="用户名" width="140"></el-table-column>
        <el-table-column prop="email" label="邮箱" width="120"></el-table-column>
        <el-table-column prop="telephone" label="电话" width="120"></el-table-column>
        <el-table-column prop="address" label="地址"></el-table-column>
        <el-table-column prop="operation" label="操作"width="240" align="center">
            <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit-outline"></i></el-button>
                <el-popconfirm
                        class="ml-5"
                        confirm-button-text='好的'
                        cancel-button-text='我在想想'
                        icon="el-icon-info"
                        icon-color="red"
                        title="您确定删除吗？"
                        @confirm="handleDelete(scope.row.id)"
                >
                    <el-button type="danger" slot="reference" >删除<i class="el-icon-remove-outline"></i></el-button>
                </el-popconfirm>
            </template>
        </el-table-column>
    </el-table>
    <!--分页 选页面-->
    <div style="padding: 10px 0">
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[2, 5, 10, 20]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
    </div>
    <el-dialog title="新增用户信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form label-width="80px" size="small" >
            <el-form-item label="用户名">
                <el-input v-model="form.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话">
                <el-input v-model="form.telephone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址">
                <el-input v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
        </div>
    </el-dialog>
</div>
</template>

<script>
    export default {
        name: "User",
        data(){
            return{
                tableData: [],
                total: 0,
                pageNum: 1,
                pageSize: 10,
                integration: "",
                // username:"",
                form: {},
                dialogFormVisible: false,
                multipleSelection: [],
                headerBg: 'headerBg'
            }
        },
        created() {
            //请求分页查询数据
            this.load()
        },
        methods:{
            load(){
                /*fetch请求分页查询*/
                // fetch("http://localhost:8888/user/page?pageNum="+this.pageNum+ "&pageSize="+ this.pageSize+ "&integration=" + this.integration ).then(res => res.json()).then(res =>{
                //     console.log(res)
                //     this.tableData =res.data
                //     this.total=res.total
                // })
                /*Axios请求分页查询*/
                this.request.get("/user/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        integration: this.integration
                    }
                }).then(res => {
                    console.log(res)

                    this.tableData = res.data.records
                    this.total = res.data.total

                })
            },
            save(){
                this.request.post("/user/",this.form).then( res =>{
                    if (res.data){
                        this.$message.success("保存成功")
                        this.dialogFormVisible = false
                        this.load()
                    }else {
                        this.$message.error("保存失败")
                    }
                })
            },
            reset(){
                this.integration=""
                this.load()
            },
            handleSelectionChange(val){//删除确认提醒框
                console.log(val)
                this.multipleSelection = val
            },
            deleteBatch(){
                let ids =  this.multipleSelection.map(v => v.id)// 因为后端的是List数组 而这ids是对象数组 所以要用前端的map(v => v.id)把对象数组 [{},{},{}] 转变成纯id的数组 [1,2,3,...]
                this.request.post("/user/del/batch/",ids).then(res => {
                    if(res.data){
                        this.$message.success("批量删除成功")
                        this.load()
                    }else {
                        this.$message.error("批量删除失败")
                    }
                })
            },
            handleEdit(row){
                this.form = row
                this.dialogFormVisible = true
            },
            handleDelete(id){
                this.request.delete("/user/" + id).then(res => {
                    if (res.data){
                        this.$message.success("删除成功")
                        this.load()
                    }else {
                        this.$message.error("删除失败")
                    }
                })
            },
            handleAdd(){
                this.dialogFormVisible=true
                this.form={}
            },
            handleSizeChange(pageSize){
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum){
                this.pageNum = pageNum
                this.load()
            },
            exp(){
                window.open("http://localhost:8888/user/export")
            },
            handleExcelImportSuccess(){
                this.$message.success("导入上传成功")
                this.load()
            }

        }
    }
</script>

<style>
    .headerBg{
        background:#eee!important;

    }
</style>