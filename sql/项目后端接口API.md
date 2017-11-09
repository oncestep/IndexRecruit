项目后端接口API
---
* 功能：主页信息输出
    * 访问路径：/
    * 访问方式：Get
    * 访问路径参数：
        * 无
    * 返回数据对象：
        * 用户信息：User Entity user
            * UserEntity实体类属性
        * 推荐职位列表（分页）：PageInfo<PositionEntity> posInfo
            * 当前页：int pageNum
            * 每页数量：int pageSize
            * 当前页的数量：int size
            * 当前页面第一个元素在数据库中的行号：int startRow
            * 当前页面最后一个元素在数据库中的行号：int endRow
            * 总记录数： long total
            * 总页数：int pages
            * 结果集：List<对应Entity类> list
            * 第一页：int firstPage
            * 前一页：int prePage
            * 下一页：int nextPage
            * 最后一页：int lastPage
            * 是否为第一页：boolean isFirstPage
            * 是否为最后一页：boolean isLastPag
            * 是否有前一页：boolean hasPreviousPage
            * 是否有下一页：boolean hasNextPage
            * 导航页码数：navigatePages
            * 所有导航页号：navigatepageNums
        * 网页标题：String title    

* 功能：主页信息输出
    * 访问路径：page/{page}
    * 访问方式：Get
    * 访问路径参数：
        * int page
    * 返回数据对象：PageInfo<PositionEntity> posInfo
        * 同上
        
<hr/>

* 功能：主页搜索
    * 访问路径：search/{keyword}
    * 访问方式：Get
    * 访问路径参数：
        * String keyword
    * 返回数据对象：
        * 推荐职位列表（分页）：PageInfo<PositionEntity> posInfo
        * 标签：String tag
        * 关键字：String keyword
        * 网页标题：String title

* 功能：主页搜索
    * 访问路径：search/{keyword}/{page}
    * 访问方式：Get
    * 访问路径参数：
        * String keyword
        * int page
    * 返回数据对象：
        * 同上
    
<hr/>

* 功能：各分类所有职位输出
    * 访问路径：category/{id}
    * 访问方式：Get
    * 访问路径参数：
        * 职位分类：int id
    * 返回数据对象：
        * 职位列表（分页）：PageInfo<PositionEntity> posInfo
        * 分类对象：CategoryEntity category
        * 标签：String tag
        * 网页标题：String title

* 功能：各分类所有职位输出
    * 访问路径：category/{id}/{page}
    * 访问方式：Get
    * 访问路径参数：
        * 职位分类：int id
        * 当前页号：int page
    * 返回数据对象：
        * 同上

<hr/>

* 功能：职位细节页
    * 访问路径：position/{id}
    * 访问方式：Get
    * 访问路径参数：
        * 职位Id:int id
    * 返回数据对象：
        * 职位：PositionEntity position
        * 部门：DepartmentEntity department
        * 公司：CompanyEntity company
        * 职位所属分类：CategoryEntity category
        * 评论列表（分页）：PageInfo<CommentEntity> comList
            * PageHelper分页类结果集 list 为List<UserCommentBO>
        * 网页标题：String title
    
* 功能：职位细节页
    * 访问路径：position/{id}/{page}
    * 访问方法：Get
    * 访问路径参数：
        * 职位Id:int id
        * 当前页号：int page
    * 返回数据对象：
        * 同上
    

<hr/>

* 功能：用户申请职位
    * 访问路径：position/{id}/apply
    * 访问方法：Post
    * 访问路径参数：
        * 职位Id:int id
    * 返回数据对象：
        * 无

<hr/>

* 功能：用户评论职位
    * 访问路径：position/{id}/comment
    * 访问方法：Post
    * 访问参数：
        * 职位Id:int id
        * 评论类型：int type
        * 评论内容：String content
    * 返回数据对象：
        * 无

<hr/>

* 功能：用户个人中心
    * 访问路径：user
    * 访问方法：Get
    * 访问参数：
        * (Session 内存有的当前用户user对象)
    * 返回数据对象：
        * 用户信息：UserEntity user
        * 简历信息：ResumeEntity resume
        * 收藏职位列表：List<FavorPositionBO> favorPosList
        * 个人应聘记录列表：List<ApplicationPositionHRBO> applyPosList

<hr/>

* 功能：完善用户个人简历
    * 访问路径：user/resumeUpdate
    * 访问方法：Post
    * 访问参数：
        * (Session 内存有的当前用户user对象)
        * 能力：String ability
        * 实习经历：String internship
        * 工作经历：String workExperience
        * 获奖证书：String certificate
        * 期望职位描述：String jobDesire
    * 返回数据对象：
        * 无

<hr/>

* 功能：用户更新个人信息
    * 访问路径：user/infoUpdate
    * 访问方法：Post
    * 访问参数：
        * (Session 内存有的当前用户user对象)
        * String password
        * String name
        * String nickname
        * String email
        * String city
        * String eduDegree
        * String graduation
        * String dirDesire
    * 返回数据对象：
        * 无
    
<hr/>

* 功能：用户注销
    * 访问路径：user/logout
    * 访问方法：Post
    * 访问参数：
        * (Session 内存有的当前用户user对象)
    * 返回数据对象
        * 无


        


