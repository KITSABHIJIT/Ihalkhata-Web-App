// vim: ts=4:sw=4:nu:fdc=4:nospell
/**
 * Ext.ux.form.RowActions Plugin Example Application
 *
 * @author    Ing. Jozef Sakáloš
 * @date      22. March 2008
 * @version   $Id: lovcombo.js 152 2009-06-23 20:10:42Z jozo $
 *
 * @license lovcombo.js is licensed under the terms of
 * the Open Source LGPL 3.0 license.  Commercial use is permitted to the extent
 * that the code/component(s) do NOT become part of another Open Source or Commercially
 * licensed development library or toolkit without explicit permission.
 * 
 * License details: http://www.gnu.org/licenses/lgpl.html
 */

/*global Ext, WebPage, Example, console, window */
alert("in lovcombo.js");
Ext.BLANK_IMAGE_URL = '../../com.exp.cemk.css/images/default/s.gif';
Ext.ns('Example');
Example.version = '1.0';

Ext.override(Ext.ux.form.LovCombo, {
	beforeBlur: Ext.emptyFn
});
alert("after override");
// application entry point
Ext.onReady(function() {
	alert("ion rdy");
    Ext.QuickTips.init();

			var store1=new Ext.data.SimpleStore({
			 id:0
			,fields:[{name:'id',type:'int'},
					'names']
			,data:[
				 [1,'one']
				,[11,'six']
				,[2,'seven']
				,[22,'four']
			,[3,'five']
			]
		});
			alert("aftr stre");
	var lc = new Ext.ux.form.LovCombo({
		 id:'lovcombo'
		,renderTo:'lovcomboct'
		,width:300
		,hideOnSelect:false
		,maxHeight:200,
store:store1
	/*	,store:[
			 [1, 'Personnel []']
			,[11, 'Finance (33)']
			,[5, 'Door']
			,[6, 'Door Panel']
			,[2, 'Management !77']
			,[38, 'Hello<br>There']
			,[25, 'Production']
			,[3, 'Users']
			,[20, 'Window']
			,[21, 'Window Panel']
			,[22, 'Form Panel']
			,[23, 'Grid Panel']
			,[24, 'Data View Panel']
		] */
//		,store:new Ext.data.SimpleStore({
//			 id:0
//			,fields:[{name:'id',type:'int'}, 'privGroup']
//			,data:[
//				 [1, 'Personnel']
//				,[11, 'Finance']
//				,[2, 'Management']
//				,[22, 'Production']
//				,[3, 'Users']
//			]
//		})
		,triggerAction:'all'
		,valueField:'id'
		,displayField:'names'
		,mode:'local'
	});
alert("after combo");
	});

// eof

