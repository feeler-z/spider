package com.ch.spider.entity;

import java.util.Date;

public class ArticleEntity  {
	/**
	 * 文章id
	 */
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 副标题
	 */
	private String subhead;
	/**
	 * 
	 */
	private String referer;
	/**
	 * 摘要
	 */
	private String digest;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 发布者ID
	 */
	private Integer publishId;
	/**
	 * 类型ID
	 */
	private Integer typeId;
	/**
	 * 封面图片ID
	 */
	private String coverPath;
	/**
	 * 文章正文
	 */
	private String content;
	/**
	 * 阅读次数
	 */
	private Integer pageviews;
	/**
	 * 收藏次数
	 */
	private Integer collect;
	/**
	 * 点赞次数
	 */
	private Integer thumb;
	/**
	 * 状态:0置顶1待审核2审核通过3审核未通过4草稿
	 */
	private Integer status;
	/**
	 * 发布时间
	 */
	private Date publishTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 删除标识:0有效，1隐藏
	 */
	private Integer delFlag;
	/**
	 * 标签
	 */
	private String tagwords;
	/**
	 * 稿源(网站名)
	 */
	private String resource;
	/**
	 * 版权声明
	 */
	private String copyright;
	/**
	 * 审核说明
	 */
	private String remark;
	/**
	 * 类型（模式）1原创，2转载
	 */
	private Integer mold;
	/**
	 * 
	 */
	private Integer top;

	/**
	 * 设置：文章id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：文章id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：副标题
	 */
	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}
	/**
	 * 获取：副标题
	 */
	public String getSubhead() {
		return subhead;
	}
	/**
	 * 设置：
	 */
	public void setReferer(String referer) {
		this.referer = referer;
	}
	/**
	 * 获取：
	 */
	public String getReferer() {
		return referer;
	}
	/**
	 * 设置：摘要
	 */
	public void setDigest(String digest) {
		this.digest = digest;
	}
	/**
	 * 获取：摘要
	 */
	public String getDigest() {
		return digest;
	}
	/**
	 * 设置：作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：作者
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：发布者ID
	 */
	public void setPublishId(Integer publishId) {
		this.publishId = publishId;
	}
	/**
	 * 获取：发布者ID
	 */
	public Integer getPublishId() {
		return publishId;
	}
	/**
	 * 设置：类型ID
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：类型ID
	 */
	public Integer getTypeId() {
		return typeId;
	}
	/**
	 * 设置：封面图片ID
	 */

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	/**
	 * 设置：文章正文
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：文章正文
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：阅读次数
	 */
	public void setPageviews(Integer pageviews) {
		this.pageviews = pageviews;
	}
	/**
	 * 获取：阅读次数
	 */
	public Integer getPageviews() {
		return pageviews;
	}
	/**
	 * 设置：收藏次数
	 */
	public void setCollect(Integer collect) {
		this.collect = collect;
	}
	/**
	 * 获取：收藏次数
	 */
	public Integer getCollect() {
		return collect;
	}
	/**
	 * 设置：点赞次数
	 */
	public void setThumb(Integer thumb) {
		this.thumb = thumb;
	}
	/**
	 * 获取：点赞次数
	 */
	public Integer getThumb() {
		return thumb;
	}
	/**
	 * 设置：状态:0置顶1待审核2审核通过3审核未通过4草稿
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态:0置顶1待审核2审核通过3审核未通过4草稿
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：发布时间
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getPublishTime() {
		return publishTime;
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
	 * 设置：删除标识:0有效，1隐藏
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：删除标识:0有效，1隐藏
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置：标签
	 */
	public void setTagwords(String tagwords) {
		this.tagwords = tagwords;
	}
	/**
	 * 获取：标签
	 */
	public String getTagwords() {
		return tagwords;
	}
	/**
	 * 设置：稿源(网站名)
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
	/**
	 * 获取：稿源(网站名)
	 */
	public String getResource() {
		return resource;
	}
	/**
	 * 设置：版权声明
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	/**
	 * 获取：版权声明
	 */
	public String getCopyright() {
		return copyright;
	}
	/**
	 * 设置：审核说明
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：审核说明
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：类型（模式）1原创，2转载
	 */
	public void setMold(Integer mold) {
		this.mold = mold;
	}
	/**
	 * 获取：类型（模式）1原创，2转载
	 */
	public Integer getMold() {
		return mold;
	}
	/**
	 * 设置：
	 */
	public void setTop(Integer top) {
		this.top = top;
	}
	/**
	 * 获取：
	 */
	public Integer getTop() {
		return top;
	}
}
