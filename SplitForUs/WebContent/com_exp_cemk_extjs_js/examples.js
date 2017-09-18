/*!
 * Ext JS Library 3.3.1
 * Copyright(c) 2006-2010 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */
Ext.BLANK_IMAGE_URL = '../../resources/images/default/s.gif';
function name(ab)
{
ab.style.backgroundColor = '#E0ECF8';
}
function name_out(ab)
{
ab.style.backgroundColor = '#ffffff';
}
Ext.example = function(){
    var msgCt;

    function createBox(t, s){
        return ['<div class="msg">',
                '<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
                '<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc"><h3>', t, '</h3>', s, '</div></div></div>',
                '<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
                '</div>'].join('');
    }
    return {
        msg : function(title, format){
            if(!msgCt){
                msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
            }
            msgCt.alignTo(document, 't-t');
            var s = String.format.apply(String, Array.prototype.slice.call(arguments, 1));
            var m = Ext.DomHelper.append(msgCt, {html:createBox(title, s)}, true);
            m.slideIn('t').pause(1).ghost("t", {remove:true});
        },

        init : function(){
            /*
            var t = Ext.get('exttheme');
            if(!t){ // run locally?
                return;
            }
            var theme = Cookies.get('exttheme') || 'aero';
            if(theme){
                t.dom.value = theme;
                Ext.getBody().addClass('x-'+theme);
            }
            t.on('change', function(){
                Cookies.set('exttheme', t.getValue());
                setTimeout(function(){
                    window.location.reload();
                }, 250);
            });*/

            var lb = Ext.get('lib-bar');
            if(lb){
                lb.show();
            }
        }
    };
}();
Ext.example.bogusMarksecurity = '<ul><li><table><tr><td><input type="checkbox" checked="checked"></td><td style="font-size:15px">HDFC Bank</td></tr></table></li></ul><br><br><center><input type="button" value="OK"></center>';
Ext.example.shortBogusMarkupnoti = '<p><ul><li >Notifications</li><li >Saved Queries</li><li >Report Outputs</li><li >Report Links</li><li >Quick Links</li><li >Status Of Working Case</li><li >Activities On Working Case</li><li >Activities On View Case</li><li >Log Based/System Alert Notification</li></ul>';
Ext.example.shortBogusMarkbutton = '<p><center><input type="button" value="Submit"></center></ul>';
Ext.example.shortBogusMarkalert = '<p><ul><li>IMSS Alerts</li><li>Log Based/System Alerts</li></ul>';
Ext.example.shortBogusMark = '<p><ul><li onmouseover="name(this)" onmouseout="name_out(this)">Case Creation</li><li onmouseover="name(this)" onmouseout="name_out(this)">Case Approval</li><li onmouseover="name(this)" onmouseout="name_out(this)">Case Assignment</li></ul>';
Ext.example.shortBogusMarkco = '<p><ul><li>Upload evidences Of Artifacts</li><li>Case Artifacts Sharing</li></ul>';
Ext.example.shortBogusMarkcs = '<p><ul><li onmouseover="name(this)" onmouseout="name_out(this)">Case Log</li></ul>';
Ext.example.shortBogusMarkcc = '<p><ul><li onmouseover="name(this)" onmouseout="name_out(this)">Case Archival</li><li onmouseover="name(this)" onmouseout="name_out(this)">Case Transfer Accept</li><li onmouseover="name(this)" onmouseout="name_out(this)">Case Transfer Reject</li><li onmouseover="name(this)" onmouseout="name_out(this)">Case Rejection</li></ul>';
Ext.example.shortBogusMarkup = '<p><ul><li onmouseover="name(this)" onmouseout="name_out(this)">Broker Client Concentration</li><li onmouseover="name(this)" onmouseout="name_out(this)">CounterParty Concentration Report</li><li onmouseover="name(this)" onmouseout="name_out(this)">Wash Trades Report</li><li onmouseover="name(this)" onmouseout="name_out(this)">Synchronized Trade Report</li><li onmouseover="name(this)" onmouseout="name_out(this)">Contribution Of Clients Establishing New Highs/Lows Report</li><li onmouseover="name(this)" onmouseout="name_out(this)">Price Movement And Trading Pattern</li><li onmouseover="name(this)" onmouseout="name_out(this)">Open Interest Report</li><li onmouseover="name(this)" onmouseout="name_out(this)">Price Movement Around Corporate Announcements</li><li onmouseover="name(this)" onmouseout="name_out(this)">Analysis Report</li></ul>';
Ext.example.shortBogusMarkup12 = '<p><ul><li onmouseover="name(this)" onmouseout="name_out(this)">Order Log</li><li onmouseover="name(this)" onmouseout="name_out(this)">Trade Log</li><li onmouseover="name(this)" onmouseout="name_out(this)">Transaction Log</li><li onmouseover="name(this)" onmouseout="name_out(this)">Order Log Summary</li><li onmouseover="name(this)" onmouseout="name_out(this)">Order History</li><li onmouseover="name(this)" onmouseout="name_out(this)">Order Pattern Analysis</li><li></ul>';
Ext.example.shortBogusMarkup13 = '<p><ul><li onmouseover="name(this)" onmouseout="name_out(this)">Off Market & Exchange Transaction</li><li onmouseover="name(this)" onmouseout="name_out(this)">Transaction Statement</li><li onmouseover="name(this)" onmouseout="name_out(this)">Holding Statement</li></ul>';
Ext.example.shortBogusMarkup14 = '<p><ul><li onmouseover="name(this)" onmouseout="name_out(this)">Quaterly Shareholding</li><li onmouseover="name(this)" onmouseout="name_out(this)">Financial Results</li></ul>';
Ext.example.shortBogusMarkup15 = '<p><ul><li onmouseover="name(this)" onmouseout="name_out(this)">Contribution In The Rise/Fall Of Indices</li><li onmouseover="name(this)" onmouseout="name_out(this)">Gainers/Losers</li><li onmouseover="name(this)" onmouseout="name_out(this)">Price Volume-Intraday</li><li onmouseover="name(this)" onmouseout="name_out(this)">Price Volume-Interday</li><li onmouseover="name(this)" onmouseout="name_out(this)">Daily Stock Market</li><li onmouseover="name(this)" onmouseout="name_out(this)">Volatility</li></ul>';
Ext.example.shortBogusMarkup16 = '<p><ul><li onmouseover="name(this)" onmouseout="name_out(this)">Daily Member-Wise Securities And Fund Obligation</li><li onmouseover="name(this)" onmouseout="name_out(this)">Identification Of Colluding Groups</li><li onmouseover="name(this)" onmouseout="name_out(this)">Telephone Mapping</li><li onmouseover="name(this)" onmouseout="name_out(this)">Mapping Of Banking Transactions</li><li onmouseover="name(this)" onmouseout="name_out(this)">Regulatory Actions</li></ul>';
Ext.example.shortBogusMarkup17 = '<p><ul><li onmouseover="name(this)" onmouseout="name_out(this)">Order Log</li><li onmouseover="name(this)" onmouseout="name_out(this)">Trade Log</li><li onmouseover="name(this)" onmouseout="name_out(this)">Transaction Log</li><li onmouseover="name(this)" onmouseout="name_out(this)">Order Log Summary</li><li onmouseover="name(this)" onmouseout="name_out(this)">Order History</li><li onmouseover="name(this)" onmouseout="name_out(this)">Order Pattern Analysis</li><li onmouseover="name(this)" onmouseout="name_out(this)"></ul>';
Ext.example.shortBogusMarkupss = '<br></br><table><tr><td></td><th width="200px">Case Name</th><th width="200px">Case Description</th><th width="200px">Case Creation Date</th><th width="200px">Case Owner</th></tr><tr><td><input type="checkbox"></td><td><a href="#">Case 1</a></td><td>View Case</td><td>01/01/2009</td><td>Supervisor</td></tr><tr><td><input type="checkbox"></td><td><a href="#">Case 2</a></td><td>Working Case</td><td>02/01/2009</td><td>Supervisor </td></tr><tr><td><input type="checkbox"></td><td><a href="#">Case 3</a></td><td>Working Case</td><td>03/01/2009</td><td>Supervisor</td></tr><tr><td><input type="checkbox"></td><td><a href="#">Case 4</a></td><td>View Case</td><td>04/01/2009</td><td>Supervisor</td></tr></table>';
Ext.example.shortBogusMarkupone='<p><table><tr><td align="left"><img src="../shared/techPieChart.gif"></img></td><td width="80px"></td><td align="right"><img src="../shared/techPieChart.gif"></img></td></tr></table></p>';
Ext.example.shortBogusMarkuptwo='<ul><li style="font-size:15px">ABAN</li><li style="font-size:15px">Reliance</li><li style="font-size:15px">ACC</li><li style="font-size:15px">HDFC Bank</li><li style="font-size:15px">ICICI Bank</li></ul>';
Ext.example.bogusMarkup = '<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed metus nibh, sodales a, porta at, vulputate eget, dui. Pellentesque ut nisl. Maecenas tortor turpis, interdum non, sodales non, iaculis ac, lacus. Vestibulum auctor, tortor quis iaculis malesuada, libero lectus bibendum purus, sit amet tincidunt quam turpis vel lacus. In pellentesque nisl non sem. Suspendisse nunc sem, pretium eget, cursus a, fringilla vel, urna.<br/><br/>Aliquam commodo ullamcorper erat. Nullam vel justo in neque porttitor laoreet. Aenean lacus dui, consequat eu, adipiscing eget, nonummy non, nisi. Morbi nunc est, dignissim non, ornare sed, luctus eu, massa. Vivamus eget quam. Vivamus tincidunt diam nec urna. Curabitur velit.</p>';
Ext.example.shortBogusMarkuponeim='<p><td align="right"><td><img src="../../shared/images.gif"></img></td></td></p>';
Ext.onReady(Ext.example.init, Ext.example);


// old school cookie functions
var Cookies = {};
Cookies.set = function(name, value){
     var argv = arguments;
     var argc = arguments.length;
     var expires = (argc > 2) ? argv[2] : null;
     var path = (argc > 3) ? argv[3] : '/';
     var domain = (argc > 4) ? argv[4] : null;
     var secure = (argc > 5) ? argv[5] : false;
     document.cookie = name + "=" + escape (value) +
       ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
       ((path == null) ? "" : ("; path=" + path)) +
       ((domain == null) ? "" : ("; domain=" + domain)) +
       ((secure == true) ? "; secure" : "");
};

Cookies.get = function(name){
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	var j = 0;
	while(i < clen){
		j = i + alen;
		if (document.cookie.substring(i, j) == arg)
			return Cookies.getCookieVal(j);
		i = document.cookie.indexOf(" ", i) + 1;
		if(i == 0)
			break;
	}
	return null;
};

Cookies.clear = function(name) {
  if(Cookies.get(name)){
    document.cookie = name + "=" +
    "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
};

Cookies.getCookieVal = function(offset){
   var endstr = document.cookie.indexOf(";", offset);
   if(endstr == -1){
       endstr = document.cookie.length;
   }
   return unescape(document.cookie.substring(offset, endstr));
};