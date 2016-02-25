Ext.app.XyChooseUserEx = Ext.extend(Ext.form.TriggerField,{
					m_window : null,
					shadow : "sides",
					allowBlur : true,
					displayField : 'column0',
					valueField : null,
					outterEditor : null,
					validationEvent : false,
					validateOnBlur : false,
					feetypeid : null,
					readOnly : true,
					trigger1Class : 'x-form-search-trigger',
					trigger2Class : 'x-form-clear-trigger',
					rootTitle : '请选择收款人/供应商',
					dataUrl : null,
					openNum : 0,
					winWidth : 600,
					winHeight : 450,
					param : null,
					columnModel : null,
					columnGYModel : null,
					scriptPath : null,
					sqlFile : null,
					sqlFileGY : null,
					initComponent : function() {
						this.triggerConfig = {
							tag : "span",
							cls : "x-form-twin-triggers",
							cn : [ {
								tag : "img",
								src : Ext.BLANK_IMAGE_URL,
								cls : "x-form-trigger " + this.trigger1Class
							} ]
						}
						Ext.app.XyChooseUserEx.superclass.initComponent.call(this);
					},
					initTrigger : function() {
						var A = this.trigger.select(".x-form-trigger", true);
						this.wrap.setStyle("overflow", "hidden");
						var B = this;
						A.each(function(D, F, C) {
							D.hide = function() {
								var G = B.wrap.getWidth();
								this.dom.style.display = "none";
								B.el.setWidth(G - B.trigger.getWidth())
							};
							D.show = function() {
								var G = B.wrap.getWidth();
								this.dom.style.display = "";
								B.el.setWidth(G - B.trigger.getWidth())
							};
							var E = "Trigger" + (C + 1);
							if (this["hide" + E]) {
								D.dom.style.display = "none"
							}
							D.on("click", this["on" + E + "Click"], this, {
								preventDefault : true
							});
							D.addClassOnOver("x-form-trigger-over");
							D.addClassOnClick("x-form-trigger-click")
						}, this);
						this.triggers = A.elements
					},
					loadException : function(This, node, response) {
						showExtLoadException(This, node, response);
					},
					setValue : function(A) {
						if (this.rendered) {
							var s_value = "";
							if (A === undefined || A === null || A === "") {
								s_value = "";
								this.value = null;
							} else {
								if (typeof A == "object") {
								} else {
									A = JSON.parse(A);
								}
								this.value = A;
								s_value = A[this.displayField];
							}
							this.el.dom.value = s_value;
							this.validate();
						}
					},
					setXyValue : function(A) {
						if (this.rendered) {
							var s_value = "";
							if (A === undefined || A === null || A === "") {
								s_value = "";
								this.value = null;
							} else {
								if (typeof A == "object") {
								} else {
									A = JSON.parse(A);
								}
								this.value = A;
								s_value = A[this.displayField];
							}
							this.el.dom.value = s_value;
						}
					},
					onRender : function(B, A) {
						Ext.app.XyChooseUserEx.superclass.onRender.call(this,
								B, A);
						if (this.disabled) {
							this.el.dom.disabled = true;
							this.getActionEl().addClass(this.disabledClass);
						}
					},
					getValue : function() {
						return this.value;
					},
					onTrigger2Click : function() {
						var oldValue = this.getValue();
						if (this.disabled) {
							return;
						}
						this.setValue(null);
						if (this.outterEditor !== undefined
								&& this.outterEditor != null) {
							this.outterEditor.completeEdit();
						}
						this.fireEvent("afterset", oldValue);
					},
					onTrigger1Click : function() {
						alert("123");
						if (this.disabled) {
							return;
						}
						var m_this = this;

						if (this.feetypeid == "" || this.feetypeid == null) {
							Ext.MessageBox.alert("提示", "请先选择费用类别");
							return;
						}

						var root = new Ext.tree.AsyncTreeNode( {
							id : "root",
							text : "请选择部门"
						});
						var m_treeloader = new Ext.tree.TreeLoader(
								{
									dataUrl : "SSC/getDeptTreeForSupplier.action?companyid="
											+ this.companyid
											+ "&userid="
											+ this.userid
								});
						m_treeloader.on("loadexception", loadException);
						var m_tree = new Ext.tree.TreePanel( {
							region : 'west',
							height : 350,
							width : 163,
							root : root,
							loader : m_treeloader,
							autoScroll : true,
							border : true,
							animate : true,
							enableDD : false,
							rootVisible : true
						});

						m_tree.on("click", tree_click);

						var clnRowNum = new Ext.grid.RowNumberer();
						var clnUserID = {
							header : "ID",
							hidden : true,
							fixed : false,
							dataIndex : "column0"
						};
						var clnUserName = {
							header : "姓名",
							sortable : true,
							width : 60,
							dataIndex : "column1",
							searchField : 'displayname'
						};
						var clnLoginName = {
							header : "编码",
							sortable : true,
							dataIndex : "column2",
							searchField : 'varname'
						};
						var clnUserBank = {
							header : "开户银行",
							sortable : true,
							dataIndex : "column3",
							searchField : 'varbank'

						};
						var clnUserCard = {
							header : "卡号",
							sortable : true,
							dataIndex : "column4",
							searchField : 'cardno'
						};
						var clnUserType = {
							header : "人员类型",
							fixed : false,
							hidden : true,
							dataIndex : "column5"
						};
						var clnRelateType = {
							header : "关联类型",
							fixed : false,
							hidden : true,
							dataIndex : "column6"
						};
						var clnRelateType1 = {
							header : "关联类型1",
							fixed : false,
							hidden : true,
							dataIndex : "column7"
						};

						var clnAccountname = {
							header : "开户名称",
							sortable : true,
							dataIndex : "column8"
						};
						var clnPhone = {
							header : "天翼手机号",
							sortable : true,
							dataIndex : "column10"
						};

						var cm = new Ext.grid.ColumnModel( [ clnRowNum,
								clnUserID, clnUserName, clnLoginName,
								clnUserBank, clnUserCard, clnUserType,
								clnRelateType, clnRelateType1, clnAccountname,clnPhone ]);

						var clnGYRowNum = new Ext.grid.RowNumberer();
						var clnGYUserID = {
							header : "ID",
							hidden : true,
							fixed : false,
							dataIndex : "column0"
						};
						var clnGYUserName = {
							header : "供应商名称",
							sortable : true,
							dataIndex : "column1",
							width : 250,
							searchField : 'displayname'
						};
						var clnGYLoginName = {
							header : "编码",
							width : 80,
							sortable : true,
							dataIndex : "column2",
							searchField : 'varname'
						};

						var clnGYBank = {
							header : "开户银行",
							sortable : true,
							dataIndex : "column3",
							searchField : 'varbank'

						};
						var clnGYCard = {
							header : "卡号",
							sortable : true,
							dataIndex : "column4",
							searchField : 'cardno'
						};
						var clnGYType = {
							header : "人员类型",
							fixed : false,
							hidden : true,
							dataIndex : "column5"
						};
						var clnGYRelateType = {
							header : "关联类型",
							fixed : false,
							hidden : true,
							dataIndex : "column6"
						};
						var clnGYRelateTypeCh = {
							header : "关联类型",
							sortable : true,
							dataIndex : "column7",
							renderer : function(value) {
								if (value == '0') {
									return '否';
								} else {
									return '是';
								}
							}
						};
						var clnAccountnameGY = {
							header : "开户名称",
							sortable : true,
							dataIndex : "column8"
						};

						var cmGY = new Ext.grid.ColumnModel( [ clnGYRowNum,
								clnGYUserID, clnGYUserName, clnGYLoginName,
								clnGYBank, clnGYCard, clnGYType,
								clnGYRelateType, clnGYRelateTypeCh,
								clnAccountnameGY ]);

						this.columnModel = cm;
						this.columnGYModel = cmGY;

						var url = this.dataUrl;
						if (url == null || url == "") {
							url = "wf/CommonGridDataAction.action";
						}

						var dataIndexArr = new Array();
						var searchArr = new Array();
						var j = 0;
						var k = 0;
						for ( var i = 0; i < this.columnModel.getColumnCount(); i++) {
							if (typeof (this.columnModel.config[i].searchField) == 'undefined'
									|| this.columnModel.config[i].searchField == '') {
							} else {
								searchArr[k] = [
										"按 "
												+ this.columnModel.config[i].header
												+ " 搜索",
										this.columnModel.config[i].searchField ];
								k++;
							}
							if (typeof (this.columnModel.getDataIndex(i)) == 'undefined'
									|| this.columnModel.getDataIndex(i) == '') {
							} else {
								dataIndexArr[j] = this.columnModel
										.getDataIndex(i);
								j++;
							}
						}

						var dataIndexArrGY = new Array();
						var searchArrGY = new Array();
						var j = 0;
						var k = 0;
						for ( var i = 0; i < this.columnGYModel.getColumnCount(); i++) {
							if (typeof (this.columnGYModel.config[i].searchField) == 'undefined'
									|| this.columnGYModel.config[i].searchField == '') {
							} else {
								searchArrGY[k] = ["按 "+ this.columnGYModel.config[i].header+ " 搜索",
										           this.columnGYModel.config[i].searchField ];
								k++;
							}
							if (typeof (this.columnGYModel.getDataIndex(i)) == 'undefined'
									|| this.columnGYModel.getDataIndex(i) == '') {
							} else {
								dataIndexArrGY[j] = this.columnGYModel
										.getDataIndex(i);
								j++;
							}
						}

						var dsGY = new Ext.data.SimpleStore( {
							fields : [ 'text', 'id' ],
							data : searchArrGY
						});
						var m_combGY = new Ext.form.ComboBox( {
							store : dsGY,
							displayField : 'text',
							valueField : "id",
							typeAhead : true,
							mode : 'local',
							triggerAction : 'all',
							selectOnFocus : true,
							width : 140,
							readOnly : true
						});

						var ds = new Ext.data.SimpleStore( {
							fields : [ 'text', 'id' ],
							data : searchArr
						});
						var m_comb = new Ext.form.ComboBox( {
							store : ds,
							displayField : 'text',
							valueField : "id",
							typeAhead : true,
							mode : 'local',
							triggerAction : 'all',
							selectOnFocus : true,
							width : 140,
							readOnly : true
						});

						m_comb.setValue(searchArr[0][1]);
						// alert(searchArr[0][1]);
						var m_loader = new Ext.data.JsonStore( {
							url : url,
							root : 'data',
							totalProperty : "totalcount",
							fields : dataIndexArr
						});
						m_loader.on("beforeload",function(GridLoader, node) {
											GridLoader.baseParams.scriptPath = m_this.scriptPath;
											GridLoader.baseParams.sqlFile = m_this.sqlFile;
											GridLoader.baseParams.totalCount = m_loader
													.getTotalCount();
											if (m_this.param != null) {
												for ( var i = 0; i < m_this.param.length; i++) {
													GridLoader.baseParams[m_this.param[i].name] = m_this.param[i].value;
												}
											}
										});
						m_loader.on("loadexception", this.loadException);

						var barPage = new Ext.PagingToolbar( {
							border : true,
							pageSize : WINDOW_PER_PAGE_SIZE,
							store : m_loader,
							displayInfo : true,
							displayMsg : '第{0}条到{1}条记录，共{2}条',
							emptyMsg : "没有记录"
						});

						var m_btnSearch = new Ext.app.SearchField(
								{
									width : 140,
									readOnly : false,
									onTrigger2Click : function() {
										m_btnSearch.el.dom.value = '';
									},
									onTrigger1Click : function() {
										m_loader.totalLength = 0;
										var selectValue = m_comb.getValue();
										var searchField = selectValue.trim();
										var searchValue = m_btnSearch.el.dom.value.trim();
										if (selectValue == null|| selectValue == "") {
											searchField = searchArrGY[0][1];
											m_comb.setRawValue(searchArrGY[0][0]);
											m_comb.setValue(searchArrGY[0][1]);
										}
										if (m_btnSearch.el.dom.value == null
												|| m_btnSearch.el.dom.value
														.trim() == "") {
											searchField = "";
											searchValue = "";
										}

										m_loader.baseParams.searchField = searchField;
										m_loader.baseParams.searchValue = searchValue;

										m_loader.baseParams.scriptPath = m_this.scriptPath;
										m_loader.baseParams.sqlFile = m_this.sqlFile;
										m_loader.baseParams.totalCount = m_loader
												.getTotalCount();
										if (m_this.param != null) {
											for ( var i = 0; i < m_this.param.length; i++) {
												m_loader.baseParams[m_this.param[i].name] = m_this.param[i].value;
											}
										}
										if (typeof (m_loader.baseParams["deptid"]) == "undefined") {
											Ext.MessageBox.alert("提示", "请先选择部门");
										} else {
											m_loader.load( {
														params : {
															start : 0,
															limit : WINDOW_PER_PAGE_SIZE
														}
													});
										}

									}
								});

						var tbar = [ '搜索: ', ' ', m_comb, '', m_btnSearch ];

						var m_Grid = new Ext.grid.GridPanel( {
							store : m_loader,
							border : true,
							colModel : this.columnModel,
							enableColumnMove : false,
							enableHdMenu : false,
							height : 350,
							selModel : new Ext.grid.RowSelectionModel( {
								singleSelect : true
							}),
							iconCls : 'icon-grid',
							loadMask : {
								msg : "数据加载中，请稍等..."
							},
							frame : true,
							tbar : tbar,
							bbar : barPage
						});

						var m_GYloader = new Ext.data.JsonStore( {
							url : url,
							root : 'data',
							totalProperty : "totalcount",
							fields : dataIndexArrGY
						});
						m_GYloader
								.on(
										"beforeload",
										function(GridLoader, node) {
											GridLoader.baseParams.scriptPath = m_this.scriptPath;
											GridLoader.baseParams.sqlFile = m_this.sqlFileGY;
											GridLoader.baseParams.totalCount = m_GYloader
													.getTotalCount();
											if (m_this.GYparam != null) {
												for ( var i = 0; i < m_this.GYparam.length; i++) {
													GridLoader.baseParams[m_this.GYparam[i].name] = m_this.GYparam[i].value;
												}
											}
										});
						m_GYloader.on("loadexception", this.loadException);
						var pagesize = new Freesky.Common.pPageSize( {
							id : "pagesize"
						});
						var barGYPage = new Ext.PagingToolbar( {
							id : "barGYPage",
							plugins : pagesize,
							border : true,
							pageSize : GY_WINDOW_PER_PAGE_SIZE,
							store : m_GYloader,
							displayInfo : true,
							displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
							emptyMsg : "没有记录"
						});

						var m_btnGYSearch = new Ext.app.SearchField(
								{
									width : 140,
									readOnly : false,
									onTrigger2Click : function() {
										m_btnGYSearch.el.dom.value = '';
									},
									onTrigger1Click : function() {
										m_GYloader.totalLength = 0;
										var selectValue = m_combGY.getValue();
										var searchField = selectValue.trim();
										var searchValue = m_btnGYSearch.el.dom.value
												.trim();
										if (selectValue == null
												|| selectValue == "") {
											// alert(searchArrGY);
											// alert(searchArr);
											// alert(searchArr[0][0]);
											// alert(searchArr[0][1]);
											searchField = searchArrGY[0][1];
											m_combGY
													.setRawValue(searchArrGY[0][0]);
											m_combGY
													.setValue(searchArrGY[0][1]);
											// searchValue = "";
										}
										if (m_btnGYSearch.el.dom.value == null
												|| m_btnGYSearch.el.dom.value
														.trim() == "") {
											searchField = "";
											searchValue = "";
										}

										m_GYloader.baseParams.searchField = searchField;
										m_GYloader.baseParams.searchValue = searchValue;

										m_GYloader.baseParams.scriptPath = m_this.scriptPath;
										m_GYloader.baseParams.sqlFile = m_this.sqlFileGY;
										m_GYloader.baseParams.totalCount = m_GYloader
												.getTotalCount();
										if (m_this.GYparam != null) {
											for ( var i = 0; i < m_this.GYparam.length; i++) {
												m_GYloader.baseParams[m_this.GYparam[i].name] = m_this.GYparam[i].value;
											}
										}
										m_GYloader
												.load( {
													params : {
														start : 0,
														limit : Ext
																.getCmp("barGYPage").pageSize
													}
												});
									}
								});

						var tGYbar = [ '搜索: ', ' ', m_combGY, ' ',
								m_btnGYSearch ];
						var m_GYGrid = new Ext.grid.GridPanel( {
							store : m_GYloader,
							border : true,
							colModel : this.columnGYModel,
							enableColumnMove : false,
							enableHdMenu : false,
							height : 350,
							width : 543,
							selModel : new Ext.grid.RowSelectionModel( {
								singleSelect : true
							}),
							iconCls : 'icon-grid',
							loadMask : {
								msg : "数据加载中，请稍等..."
							},
							frame : true,
							tbar : tGYbar,
							bbar : barGYPage
						});

						var personPanel = {
							xtype : "panel",
							layout : "column",
							border : false,
							items : [ {
								xtype : "panel",
								layout : "fit",
								columnWidth : 0.3,
								border : false,
								bodyStyle : 'padding:5px 5px 5px 5px',
								items : [ m_tree ]
							}, {
								xtype : "panel",
								layout : "fit",
								border : false,
								columnWidth : 0.7,
								items : [ m_Grid ]
							} ]
						};
						var txtField = new Ext.form.TextField( {
							maxLength : 800,
							fieldLabel : '请输入支付对象',
							labelAlign : 'right',
							allowBlank : false
						});
						var tabs = null;
						if (this.feetypeid == '1' || this.feetypeid == 1) {
							// tab.hideTabStripItem("suppler");
							// tab.unhideTabStripItem("person");
							tabs = {
								plain : false,
								activeTab : 0,
								items : [ {
									title : "选择人员",
									id : 'person',
									closable : false,
									layout : "fit",
									items : [ personPanel ]
								}
								// , {
								// title : "选择供应商",
								// id : 'suppler',
								// closable : false,
								// layout : "fit",
								// items : [m_GYGrid]
								// }
								// , {
								// title : "手工输入",
								// id : 'manual',
								// bodyStyle : 'padding:5px 5px 5px 5px',
								// closable : false,
								// layout : "form",
								// items : [txtField]
								// }
								]
							};
						} else {
							// tab.hideTabStripItem("person");
							// tab.unhideTabStripItem("suppler");
							tabs = {
								plain : false,
								activeTab : 0,
								items : [ {
									title : "选择供应商",
									id : 'suppler',
									closable : false,
									layout : "fit",
									items : [ m_GYGrid ]
								}
								// , {
								// title : "手工输入",
								// id : 'manual',
								// bodyStyle : 'padding:5px 5px 5px 5px',
								// closable : false,
								// layout : "form",
								// items : [txtField]
								// }
								]
							};
						}

						var tab = new Ext.TabPanel(tabs);
						// if(this.m_window == null)
						// {
						this.m_window = new Ext.Window( {
							title : this.rootTitle,
							modal : true,
							width : this.winWidth,
							height : this.winHeight,
							closeAction : 'hide',
							layout : 'fit',
							resizable : false,
							items : [ tab ],
							buttons : [ {
								text : '确定',
								handler : submitCall
							}, {
								text : '取消',
								handler : closeCall
							} ]
						});
						// }

						this.outterEditor.el.hide();

						this.m_window.show();

						var m_params = {
							start : 0,
							limit : GY_WINDOW_PER_PAGE_SIZE,
							totalCount : -1
						};

						m_GYloader.load( {
							params : m_params
						});

						m_GYGrid.on("dblclick", submitCall);
						m_Grid.on("dblclick", submitCall);

						function submitCall() {
							var oldValue = m_this.getValue();
							if (tab.getActiveTab().getId() == 'person') {
								var rc = m_Grid.getSelectionModel()
										.getSelected();
								if (rc == null) {
									Ext.MessageBox.alert("警告", "您还没有选择人员记录!");
									return;
								}
								m_this.setValue(rc.data);
								m_loader.un("loadexception",
										m_this.loadException);
								m_this.m_window.hide();

								if (m_this.outterEditor !== undefined
										&& m_this.outterEditor != null) {
									m_this.outterEditor.completeEdit();
								}
								m_this.fireEvent("afterset", oldValue);
								m_this.m_window = null;

							} else if (tab.getActiveTab().getId() == 'suppler') {
								var rc = m_GYGrid.getSelectionModel()
										.getSelected();
								if (rc == null) {
									Ext.MessageBox.alert("警告", "您还没有选择供应商记录!");
									return;
								}
								m_this.setValue(rc.data);

								m_GYloader.un("loadexception",
										m_this.loadException);
								m_this.m_window.hide();

								if (m_this.outterEditor !== undefined
										&& m_this.outterEditor != null) {
									m_this.outterEditor.completeEdit();
								}

								m_this.fireEvent("afterset", oldValue);
								m_this.m_window = null;
							} else {
								if (txtField.getValue() == null
										|| txtField.getValue() == "") {
									Ext.MessageBox.alert("警告", "您还没有输入支付对象!");
									return;
								}
								var json = {};
								var guidcolumn0 = {};
								json["column0"] = guidcolumn0.toGUIDString();
								json["column1"] = txtField.getValue();
								json["column2"] = guidcolumn0.toGUIDString();
								json["column3"] = "";
								json["column4"] = "";
								json["column5"] = "2";
								json["column6"] = "0";
								m_this.setXyValue(json.toJSONString());
								m_this.m_window.hide();
								if (m_this.outterEditor !== undefined
										&& m_this.outterEditor != null) {
									m_this.outterEditor.completeEdit();
								}
								m_this.fireEvent("afterset", oldValue);
								m_this.m_window = null;
							}
						}

						function tree_click(node) {
							if (node.id == 'root') {
								return;
							}
							var m_params = {
								start : 0,
								limit : WINDOW_PER_PAGE_SIZE,
								totalCount : -1
							};
							m_loader.baseParams["deptid"] = node.id;
							m_loader.load( {
								params : m_params
							});
						}

						function closeCall() {
							m_loader.un("loadexception", m_this.loadException);
							m_this.m_window.hide();
							m_this.m_window = null;
						}
					}
				});
Ext.reg("xyChooseUserEx", Ext.app.XyChooseUserEx);