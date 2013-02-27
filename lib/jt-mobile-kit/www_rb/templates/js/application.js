function onDeviceReady() {
}

$(document).ready(function () {
    document.addEventListener("deviceready", onDeviceReady, false);
    $.mobile.page.prototype.options.addBackBtn = true;
    $.mobile.allowCrossDomainPages = true;
    $.support.cors = true;

    if(refresh_page){
      refresh_page();
    }
});
