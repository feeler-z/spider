package com.ch.spider.entity;

import java.util.Date;

public class MemberEntity  {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 账户
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像id
	 */
	private Integer headId;
	/**
	 * 手机
	 */
	private String cellphone;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 
	 */
	private String imgpath;
	/**
	 * 简介
	 */
	private String introduce;
	/**
	 * 微信
	 */
	private String wechat;
	/**
	 * 微博
	 */
	private String microblog;
	/**
	 * QQ
	 */
	private String qq;
	/**
	 * 会员类型0普通会员1普通作者2专栏作者
	 */
	private Integer type;
	/**
	 * 状态0激活，1冻结
	 */
	private Integer status;
	/**
	 * 被关注次数
	 */
	private Integer attention;
	/**
	 * 发布文章数
	 */
	private Integer articlenum;
	/**
	 * 浏览数
	 */
	private Integer countread;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 删除标识
	 */
	private Integer delFlag;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 加密盐
	 */
	private String salt;
	/**
	 * 点赞数
	 */
	private Integer thumb;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：账户
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：账户
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：头像id
	 */
	public void setHeadId(Integer headId) {
		this.headId = headId;
	}
	/**
	 * 获取：头像id
	 */
	public Integer getHeadId() {
		return headId;
	}
	/**
	 * 设置：手机
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	/**
	 * 获取：手机
	 */
	public String getCellphone() {
		return cellphone;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：
	 */
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	/**
	 * 获取：
	 */
	public String getImgpath() {
		return imgpath;
	}
	/**
	 * 设置：简介
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	/**
	 * 获取：简介
	 */
	public String getIntroduce() {
		return introduce;
	}
	/**
	 * 设置：微信
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	/**
	 * 获取：微信
	 */
	public String getWechat() {
		return wechat;
	}
	/**
	 * 设置：微博
	 */
	public void setMicroblog(String microblog) {
		this.microblog = microblog;
	}
	/**
	 * 获取：微博
	 */
	public String getMicroblog() {
		return microblog;
	}
	/**
	 * 设置：QQ
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 获取：QQ
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * 设置：会员类型0普通会员1普通作者2专栏作者
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：会员类型0普通会员1普通作者2专栏作者
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：状态0激活，1冻结
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态0激活，1冻结
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：被关注次数
	 */
	public void setAttention(Integer attention) {
		this.attention = attention;
	}
	/**
	 * 获取：被关注次数
	 */
	public Integer getAttention() {
		return attention;
	}
	/**
	 * 设置：发布文章数
	 */
	public void setArticlenum(Integer articlenum) {
		this.articlenum = articlenum;
	}
	/**
	 * 获取：发布文章数
	 */
	public Integer getArticlenum() {
		return articlenum;
	}
	/**
	 * 设置：浏览数
	 */
	public void setCountread(Integer countread) {
		this.countread = countread;
	}
	/**
	 * 获取：浏览数
	 */
	public Integer getCountread() {
		return countread;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：删除标识
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：删除标识
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：加密盐
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 获取：加密盐
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * 设置：点赞数
	 */
	public void setThumb(Integer thumb) {
		this.thumb = thumb;
	}
	/**
	 * 获取：点赞数
	 */
	public Integer getThumb() {
		return thumb;
	}
}
