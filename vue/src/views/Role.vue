<template>
<div>
    <div style="padding: 10px 0;text-align: right" >
        <el-input style="width: 500px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
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
<!--        <el-upload-->
<!--                action="http://localhost:8888/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">-->
<!--        <el-button type="primary" class="ml-5">导入<i class="el-icon-bottom"></i></el-button>-->
<!--        </el-upload>-->
<!--        <el-button type="primary" class="ml-5" @click="exp">导出<i class="el-icon-top"></i></el-button>-->
    </div>
    <!--反馈数据库表格-->
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="id" label="ID" width="80"></el-table-column>
        <el-table-column prop="name" label="名称" ></el-table-column>
        <el-table-column prop="flag" label="唯一标识"></el-table-column>
        <el-table-column prop="description" label="描述"></el-table-column>
        <el-table-column prop="operation" label="操作"width="280" align="center">
            <template slot-scope="scope">
                <el-button type="info" @click="selectMenu(scope.row)" >分配菜单<i class="el-icon-menu"></i></el-button>
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
    <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form label-width="80px" size="small">
            <el-form-item label="名称">
                <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="唯一标识">
                <el-input v-model="form.flag" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="描述">
                <el-input v-model="form.description" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
        </div>
    </el-dialog>

    <el-dialog title="菜单分配" :visible.sync="menuDialogVis" width="30%">
        <el-tree
                :props="props"
                :data="menuData"
                show-checkbox
                node-key="id"
                ref="tree"
                :default-expanded-keys="expends"
                :default-checked-keys="checks">
         <span class="custom-tree-node" slot-scope="{ node, data }">
            <span><i :class="data.icon"></i> {{ data.name }}</span>
         </span>
        </el-tree>
        <div slot="footer" class="dialog-footer">
            <el-button @click="menuDialogVis = false">取 消</el-button>
            <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
        </div>
    </el-dialog>
</div>
</template>

<script>
    export default {
        name: "Role",
        data(){
            return{
                tableData: [],
                total: 0,
                pageNum: 1,
                pageSize: 10,
                name: "",
                // username:"",
                form: {},
                dialogFormVisible: false,
                menuDialogVis:false,
                multipleSelection: [],
                headerBg: 'headerBg',
                menuData: [],
                props: {
                    label: 'name',
                },
                expends: [],
                checks: [],
                roleId: 0,
                roleFlag: '',
                ids: []
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
                this.request.get("/role/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        name: this.name
                    }
                }).then(res => {
                    console.log(res)
                    this.tableData = res.data.records
                    this.total = res.data.total
                })

                this.request.get("/menu/ids").then(r => {
                    this.ids = r.data
                })
            },
            save(){
                this.request.post("/role/",this.form).then( res =>{
                    if (res.code === '200'){
                        this.$message.success("保存成功")
                        this.dialogFormVisible = false
                        this.load()
                    }else {
                        this.$message.error("保存失败")
                    }
                })
            },saveRoleMenu() {
                this.request.post("/role/roleMenu/" + this.roleId, this.$refs.tree.getCheckedKeys()).then(res => {
                    if (res.code === '200') {
                        this.$message.success("绑定成功")
                        this.menuDialogVis = false

                        // 操作管理员角色后需要重新登录
                        if (this.roleFlag === 'ROLE_ADMIN') {
                            this.$store.commit("logout")
                        }

                    } else {
                        this.$message.error(res.msg)
                    }
                })
            },
            reset(){
                this.name=""
                this.load()
            },
            handleSelectionChange(val){//删除确认提醒框
                console.log(val)
                this.multipleSelection = val
            },
            deleteBatch(){
                let ids =  this.multipleSelection.map(v => v.id)// 因为后端的是List数组 而这ids是对象数组 所以要用前端的map(v => v.id)把对象数组 [{},{},{}] 转变成纯id的数组 [1,2,3,...]
                this.request.post("/role/del/batch/",ids).then(res => {
                    if(res.code === '200'){
                        this.$message.success("批量删除成功")
                        this.load()
                    }else {
                        this.$message.error("批量删除失败")
                    }
                })
            },
            handleEdit(row){
                this.form = JSON.parse(JSON.stringify(row))
                this.dialogFormVisible = true
            },
            handleDelete(id){
                this.request.delete("/role/" + id).then(res => {
                    if (res.code === '200'){
                        this.$message.success("删除成功")
                        this.load()
                    }else {
                        this.$message.error("删除失败")
                    }
                })
            },
            handleAdd(){
                this.dialogFormVisible=true
                this.form = {}
            },
            handleSizeChange(pageSize){
                console.log(pageSize)
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum){
                console.log(pageNum)
                this.pageNum = pageNum
                this.load()
            },
            async selectMenu(role) {
                this.roleId = role.id
                this.roleFlag = role.flag

                // 请求菜单数据
                this.request.get("/menu").then(res => {
                    this.menuData = res.data

                    // 把后台返回的菜单数据处理成 id数组
                    this.expends = this.menuData.map(v => v.id)
                })

                this.request.get("/role/roleMenu/" + this.roleId).then(res => {
                    // this.menuDialogVis = true
                    this.checks = res.data
                    // this.request.get("/menu/ids").then(r=>{ const ids=r.data
                    //     ids.forEach(id => {
                    //     if (!this.checks.includes(id)) {this.$refs.tree.setChecked(id, false)} })
                    this.ids.forEach(id => {
                        if (!this.checks.includes(id)) {
                            // 可能会报错：Uncaught (in promise) TypeError: Cannot read properties of undefined (reading 'setChecked')原因是因为tree还没数据就调用setChecked方法导致undefined
                            this.$nextTick(() => { //在下一个时段处理 也就是把tree渲染出来有数据在调用setChecked方法
                                this.$refs.tree.setChecked(id, false)
                            })
                        }
                    })
                    this.menuDialogVis = true
                })
            },
        }
    }
</script>

<style>
    .headerBg{
        background:#eee!important;

    }
</style>
