<template>
    <el-container style="min-height: 100vh">
        <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
            <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
                     background-color="rgb(48, 65, 86)"
                     text-color="#fff"
                     active-text-color="#ffd04b"
                     :collapse-transition="false"
                     :collapse="isCollapse"
            >
<!--左导航栏-->
                <div style="height: 60px; line-height: 60px; text-align: center">
                    <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; margin-right: 5px">
                    <b style="color: white" v-show="logoTextShow">后台管理系统</b>
                </div>
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-message"></i>
                        <span slot="title">导航一</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title">分组一</template>
                        <el-menu-item index="1-1">选项1</el-menu-item>
                        <el-menu-item index="1-2">选项2</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="分组2">
                        <el-menu-item index="1-3">选项3</el-menu-item>
                    </el-menu-item-group>
                    <el-submenu index="1-4">
                        <template slot="title">选项4</template>
                        <el-menu-item index="1-4-1">选项4-1</el-menu-item>
                    </el-submenu>
                </el-submenu>
                <el-submenu index="2">
                    <template slot="title">
                        <i class="el-icon-menu"></i>
                        <span slot="title">导航二</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title">分组一</template>
                        <el-menu-item index="2-1">选项1</el-menu-item>
                        <el-menu-item index="2-2">选项2</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="分组2">
                        <el-menu-item index="2-3">选项3</el-menu-item>
                    </el-menu-item-group>
                    <el-submenu index="2-4">
                        <template slot="title">选项4</template>
                        <el-menu-item index="2-4-1">选项4-1</el-menu-item>
                    </el-submenu>
                </el-submenu>
                <el-submenu index="3">
                    <template slot="title">
                        <i class="el-icon-setting"></i>
                        <span slot="title">导航三</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title">分组一</template>
                        <el-menu-item index="3-1">选项1</el-menu-item>
                        <el-menu-item index="3-2">选项2</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="分组2">
                        <el-menu-item index="3-3">选项3</el-menu-item>
                    </el-menu-item-group>
                    <el-submenu index="3-4">
                        <template slot="title">选项4</template>
                        <el-menu-item index="3-4-1">选项4-1</el-menu-item>
                    </el-submenu>
                </el-submenu>
            </el-menu>
        </el-aside>

        <el-container>

            <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
                <div style="flex: 1; font-size: 20px">
                    <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
                </div>
                <el-dropdown style="width: 70px; cursor: pointer">
                    <span>王小美</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>个人信息</el-dropdown-item>
                        <el-dropdown-item>退出</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>
            <el-main>
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item><a href="/">用户管理</a></el-breadcrumb-item>
                </el-breadcrumb>
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
                    <el-button type="primary" class="ml-5">导入<i class="el-icon-bottom"></i></el-button>
                    <el-button type="primary">导出<i class="el-icon-top"></i></el-button>
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
                            <el-input v-model="form.phone" autocomplete="off"></el-input>
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
            </el-main>

        </el-container>
    </el-container>
</template>

<script>
    import request from "@/utils/request";
    export default {
        name: 'Home',
        data() {

            return {
                tableData: [],
                total: 0,
                pageNum: 1,
                pageSize: 2,
                integration: "",
                dialogFormVisible: false,
                form: {},
                multipleSelection: [],
                // username:"",
                msg: "hello",
                collapseBtnClass: 'el-icon-s-fold',
                isCollapse: false,
                sideWidth: 200,
                logoTextShow: true,
                headerBg:'headerBg'

            }
        },
        created() {
            //请求分页查询数据
           this.load()
        },
        methods: {
            collapse() {  // 点击收缩按钮触发
                this.isCollapse = !this.isCollapse
                if (this.isCollapse) {  // 收缩
                    this.sideWidth = 64
                    this.collapseBtnClass = 'el-icon-s-unfold'
                    this.logoTextShow = false
                } else {   // 展开
                    this.sideWidth = 200
                    this.collapseBtnClass = 'el-icon-s-fold'
                    this.logoTextShow = true
                }
            },
            load(){
                /*fetch请求分页查询*/
                // fetch("http://localhost:8888/user/page?pageNum="+this.pageNum+ "&pageSize="+ this.pageSize+ "&integration=" + this.integration ).then(res => res.json()).then(res =>{
                //     console.log(res)
                //     this.tableData =res.data
                //     this.total=res.total
                // })
              /*Axios请求分页查询*/
                request.get("/user/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        integration: this.integration
                    }
                }).then(res => {
                    console.log(res)

                    this.tableData = res.records
                    this.total = res.total

                })
            },
            save(){
                request.post("/user/",this.form).then( res =>{
                    if (res){
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
                request.post("/user/del/batch/",ids).then(res => {
                    if(res){
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
                request.delete("/user/" + id).then(res => {
                    if (res){
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
            }

        }


    }
</script>

<style>
    .headerBg{
        background:#eee!important;

    }
</style>