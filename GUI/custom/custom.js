/**
 * Created by Fede on 19/05/2014.
 */
function deviceReady() {
    $("#click2").on("click", function (e) {
        mui.viewPort.showPage("mui-viewport-page2", "SLIDE_LEFT");
    });

}