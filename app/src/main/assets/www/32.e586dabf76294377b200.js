(self.webpackChunkapp=self.webpackChunkapp||[]).push([[32],{32:(e,t,s)=>{"use strict";s.r(t),s.d(t,{LeaderboardModule:()=>w});var i=s(1116),r=s(4587),n=s(529),o=s(8619),c=s(4345),h=s(3492),l=s(6710),a=(s(2398),s(6162)),d=(s(7699),s(992));const u=function(e){return{me_bg:e}};function p(e,t){if(1&e&&(o.TgZ(0,"div",6),o.TgZ(1,"div",7),o.TgZ(2,"div",8),o._uU(3),o.qZA(),o._UZ(4,"img",9),o._uU(5),o.qZA(),o.TgZ(6,"div",10),o.TgZ(7,"span",11),o._uU(8),o.qZA(),o.qZA(),o.qZA()),2&e){const e=t.$implicit,s=t.index,i=o.oxw();o.Q6J("ngClass",o.VKq(5,u,e._id==i.userData._id)),o.xp6(3),o.hij("0",s+1,""),o.xp6(1),o.Q6J("src",e.user.profile_pic?i.environmentUrl+"/profile/"+e.user.profile_pic:"assets/images/profile_pic_02.png",o.LSH),o.xp6(1),o.hij(" ",e.user.name,""),o.xp6(3),o.Oqu(e.points)}}const g=[{path:"",component:(()=>{class e{constructor(e,t,s,i){this.userService=e,this.toastr=t,this.spinner=s,this.jwtService=i,this.leaderboard=[],this.environmentUrl=n.N.api_url}ngOnInit(){this.userData=this.jwtService.getUserData(),this.getLeaderboard()}getLeaderboard(){this.spinner.show(),this.userService.getLeaderboard().subscribe(e=>{this.spinner.hide(),this.leaderboard=e.data},e=>{this.spinner.hide(),this.toastr.error(e.message,"Error")})}}return e.\u0275fac=function(t){return new(t||e)(o.Y36(c.K),o.Y36(h._W),o.Y36(l.t2),o.Y36(a.Tj))},e.\u0275cmp=o.Xpm({type:e,selectors:[["app-leaderboard"]],decls:8,vars:4,consts:[[3,"showMenu","backUrl","showSearch"],[1,"page_mid_area"],[1,"other_dig_ele_wrp"],[1,"other_dig_ele_con_w"],[1,"other_dig_ele_con_w_in","setting_in_wrp"],["class","score_p leaderbord_list",3,"ngClass",4,"ngFor","ngForOf"],[1,"score_p","leaderbord_list",3,"ngClass"],[1,"score_p_left"],[1,"leaderbord_num"],[1,"profile_pic",3,"src"],[1,"score_p_right"],[1,"scr_vw"]],template:function(e,t){1&e&&(o._UZ(0,"app-header",0),o.TgZ(1,"div",1),o.TgZ(2,"div",2),o.TgZ(3,"h2"),o._uU(4,"Leaderboard"),o.qZA(),o.TgZ(5,"div",3),o.TgZ(6,"div",4),o.YNc(7,p,9,7,"div",5),o.qZA(),o.qZA(),o.qZA(),o.qZA()),2&e&&(o.Q6J("showMenu",!0)("backUrl","")("showSearch",!1),o.xp6(7),o.Q6J("ngForOf",t.leaderboard))},directives:[d.G,i.sg,i.mk],styles:[""]}),e})()}];let _=(()=>{class e{}return e.\u0275fac=function(t){return new(t||e)},e.\u0275mod=o.oAB({type:e}),e.\u0275inj=o.cJS({imports:[[r.Bz.forChild(g)],r.Bz]}),e})();var f=s(5425);let w=(()=>{class e{}return e.\u0275fac=function(t){return new(t||e)},e.\u0275mod=o.oAB({type:e}),e.\u0275inj=o.cJS({imports:[[i.ez,_,f.m]]}),e})()},992:(e,t,s)=>{"use strict";s.d(t,{G:()=>O});var i=s(3559),r=s(6673),n=s(7570);class o extends n.w{constructor(e,t){super()}schedule(e,t=0){return this}}class c extends o{constructor(e,t){super(e,t),this.scheduler=e,this.work=t,this.pending=!1}schedule(e,t=0){if(this.closed)return this;this.state=e;const s=this.id,i=this.scheduler;return null!=s&&(this.id=this.recycleAsyncId(i,s,t)),this.pending=!0,this.delay=t,this.id=this.id||this.requestAsyncId(i,this.id,t),this}requestAsyncId(e,t,s=0){return setInterval(e.flush.bind(e,this),s)}recycleAsyncId(e,t,s=0){if(null!==s&&this.delay===s&&!1===this.pending)return t;clearInterval(t)}execute(e,t){if(this.closed)return new Error("executing a cancelled action");this.pending=!1;const s=this._execute(e,t);if(s)return s;!1===this.pending&&null!=this.id&&(this.id=this.recycleAsyncId(this.scheduler,this.id,null))}_execute(e,t){let s,i=!1;try{this.work(e)}catch(r){i=!0,s=!!r&&r||new Error(r)}if(i)return this.unsubscribe(),s}_unsubscribe(){const e=this.id,t=this.scheduler,s=t.actions,i=s.indexOf(this);this.work=null,this.state=null,this.pending=!1,this.scheduler=null,-1!==i&&s.splice(i,1),null!=e&&(this.id=this.recycleAsyncId(t,e,null)),this.delay=null}}let h=(()=>{class e{constructor(t,s=e.now){this.SchedulerAction=t,this.now=s}schedule(e,t=0,s){return new this.SchedulerAction(this,e).schedule(s,t)}}return e.now=()=>Date.now(),e})();class l extends h{constructor(e,t=h.now){super(e,()=>l.delegate&&l.delegate!==this?l.delegate.now():t()),this.actions=[],this.active=!1,this.scheduled=void 0}schedule(e,t=0,s){return l.delegate&&l.delegate!==this?l.delegate.schedule(e,t,s):super.schedule(e,t,s)}flush(e){const{actions:t}=this;if(this.active)return void t.push(e);let s;this.active=!0;do{if(s=e.execute(e.state,e.delay))break}while(e=t.shift());if(this.active=!1,s){for(;e=t.shift();)e.unsubscribe();throw s}}}const a=new l(c);class d{constructor(e,t){this.dueTime=e,this.scheduler=t}call(e,t){return t.subscribe(new u(e,this.dueTime,this.scheduler))}}class u extends r.L{constructor(e,t,s){super(e),this.dueTime=t,this.scheduler=s,this.debouncedSubscription=null,this.lastValue=null,this.hasValue=!1}_next(e){this.clearDebounce(),this.lastValue=e,this.hasValue=!0,this.add(this.debouncedSubscription=this.scheduler.schedule(p,this.dueTime,this))}_complete(){this.debouncedNext(),this.destination.complete()}debouncedNext(){if(this.clearDebounce(),this.hasValue){const{lastValue:e}=this;this.lastValue=null,this.hasValue=!1,this.destination.next(e)}}clearDebounce(){const e=this.debouncedSubscription;null!==e&&(this.remove(e),e.unsubscribe(),this.debouncedSubscription=null)}}function p(e){e.debouncedNext()}class g{constructor(e,t){this.compare=e,this.keySelector=t}call(e,t){return t.subscribe(new _(e,this.compare,this.keySelector))}}class _ extends r.L{constructor(e,t,s){super(e),this.keySelector=s,this.hasKey=!1,"function"==typeof t&&(this.compare=t)}compare(e,t){return e===t}_next(e){let t;try{const{keySelector:s}=this;t=s?s(e):e}catch(i){return this.destination.error(i)}let s=!1;if(this.hasKey)try{const{compare:e}=this;s=e(this.key,t)}catch(i){return this.destination.error(i)}else this.hasKey=!0;s||(this.key=t,this.destination.next(e))}}var f=s(8619),w=s(6710),m=s(4345),v=s(3492),b=s(4587),Z=s(1116),y=s(1462);function x(e,t){1&e&&(f.TgZ(0,"a",7),f._UZ(1,"img",8),f.qZA())}const A=function(e){return[e]};function k(e,t){if(1&e&&(f.TgZ(0,"a",9),f._UZ(1,"img",10),f.qZA()),2&e){const e=f.oxw();f.Q6J("routerLink",f.VKq(1,A,e.backUrl))}}function T(e,t){if(1&e){const e=f.EpF();f.TgZ(0,"li",19),f.NdJ("click",function(){const t=f.CHM(e).$implicit;return f.oxw(2).selectResult(t)}),f._uU(1),f.qZA()}if(2&e){const e=t.$implicit;f.xp6(1),f.hij(" ",e.title," ")}}function S(e,t){if(1&e&&(f.TgZ(0,"li",9),f._uU(1),f.qZA()),2&e){const e=t.$implicit;f.Q6J("routerLink",f.VKq(2,A,e.url)),f.xp6(1),f.hij(" ",e.title," ")}}const q=function(){return{standalone:!0}};function M(e,t){if(1&e){const e=f.EpF();f.TgZ(0,"div",11),f.TgZ(1,"ul",12),f.YNc(2,T,2,1,"li",13),f.YNc(3,S,2,4,"li",14),f.qZA(),f.TgZ(4,"div",15),f.TgZ(5,"form"),f.TgZ(6,"div",16),f.TgZ(7,"input",17),f.NdJ("ngModelChange",function(t){return f.CHM(e),f.oxw().changed(t)})("ngModelChange",function(t){return f.CHM(e),f.oxw().keyword=t}),f.qZA(),f._UZ(8,"input",18),f.qZA(),f.qZA(),f.qZA(),f.qZA()}if(2&e){const e=f.oxw();f.xp6(2),f.Q6J("ngForOf",e.searchResults),f.xp6(1),f.Q6J("ngForOf",e.lsearchResults),f.xp6(4),f.Q6J("ngModel",e.keyword)("ngModelOptions",f.DdM(4,q))}}let O=(()=>{class e{constructor(e,t,s,r){this.spinner=e,this.userService=t,this.toastr=s,this.router=r,this.backUrl="",this.showMenu=!1,this.showSearch=!0,this.searchResults=[],this.modelChanged=new i.xQ,this.keyword="",this.searchItems=[{title:"Timewall",url:"/timewall"},{title:"Videowall",url:"/videowall"},{title:"Holofan",url:"/holofan"},{title:"Rotoscope",url:"/rotoscope"},{title:"MR Zone",url:"/mrzone"},{title:"VR Zone",url:"/vrzone"}],this.lsearchResults=[],this.modelChanged.pipe(function(e,t=a){return s=>s.lift(new d(e,t))}(1e3),e=>e.lift(new g(void 0,void 0))).subscribe(e=>{this.search(e)})}ngOnInit(){this.showSearch?$("#main-body").removeClass("nosearch_div_head"):$("#main-body").addClass("nosearch_div_head")}search(e){e.length>2?(this.spinner.show(),this.userService.search({keyword:e}).subscribe(t=>{this.spinner.hide(),200===t.statusCode?(this.searchResults=t.data.results,this.lsearchResults=this.searchItems.filter(t=>t.title.toLowerCase().includes(e.toLowerCase()))):this.toastr.error(t.message,"Error")},e=>{this.spinner.hide(),this.toastr.error("Something went wrong","Error")})):(this.searchResults=[],this.lsearchResults=[])}selectResult(e){"TIMEWALL"===e.type?this.router.navigate(["/timewall/detail",e._id]):"ROTOSCOPE"===e.type?this.router.navigate(["/rotoscope/detail",e._id]):"VIDEOWALL"===e.type?this.router.navigate(["/videowall/detail",e._id]):"HOLOFAN"===e.type?this.router.navigate(["/holofan/detail",e._id]):"MRZONE"===e.type?this.router.navigate(["/mrzone/detail",e._id]):"VRZONE"===e.type&&this.router.navigate(["/vrzone/detail",e._id])}changed(e){this.modelChanged.next(e)}}return e.\u0275fac=function(t){return new(t||e)(f.Y36(w.t2),f.Y36(m.K),f.Y36(v._W),f.Y36(b.F0))},e.\u0275cmp=f.Xpm({type:e,selectors:[["app-header"]],inputs:{backUrl:"backUrl",showMenu:"showMenu",showSearch:"showSearch"},decls:8,vars:3,consts:[[1,"header_p"],[1,"left_icon_h"],["href","javascript:void(0)","data-toggle","modal","data-target","#myModal",4,"ngIf"],[3,"routerLink",4,"ngIf"],[1,"mid_con_h"],["src","assets/images/head_logo.png",1,"head_logo"],["class","srh_fild_h_w_nw",4,"ngIf"],["href","javascript:void(0)","data-toggle","modal","data-target","#myModal"],["src","assets/images/menu_icon.png",1,"menu_icon"],[3,"routerLink"],["src","assets/images/backarrow_w.png",1,"menu_icon"],[1,"srh_fild_h_w_nw"],[1,"search_list_w"],[3,"click",4,"ngFor","ngForOf"],[3,"routerLink",4,"ngFor","ngForOf"],[1,"search_top"],[1,"srh_fild_h_w"],["type","text","placeholder","Search",1,"srh_fild_h",3,"ngModel","ngModelOptions","ngModelChange"],["type","button",1,"srh_icon"],[3,"click"]],template:function(e,t){1&e&&(f.TgZ(0,"div",0),f.TgZ(1,"div",1),f.YNc(2,x,2,0,"a",2),f.YNc(3,k,2,3,"a",3),f.qZA(),f.TgZ(4,"div",4),f._uU(5,"IndianOil "),f._UZ(6,"img",5),f.qZA(),f.qZA(),f.YNc(7,M,9,5,"div",6)),2&e&&(f.xp6(2),f.Q6J("ngIf",t.showMenu),f.xp6(1),f.Q6J("ngIf",t.backUrl),f.xp6(4),f.Q6J("ngIf",t.showSearch))},directives:[Z.O5,b.yS,Z.sg,y._Y,y.JL,y.F,y.Fj,y.JJ,y.On,b.rH],styles:[""]}),e})()}}]);