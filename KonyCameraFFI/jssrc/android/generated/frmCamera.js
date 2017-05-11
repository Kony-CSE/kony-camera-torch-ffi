function addWidgetsfrmCamera() {
    frmCamera.setDefaultUnit(kony.flex.DP);
    var btnStartTorch = new kony.ui.Button({
        "focusSkin": "slButtonGlossRed",
        "height": "50dp",
        "id": "btnStartTorch",
        "isVisible": true,
        "left": "50dp",
        "onClick": AS_Button_8996f6a9b05e4a6abe522a1bdd5f7d1f,
        "skin": "slButtonGlossBlue",
        "text": "Start Torch",
        "top": "55dp",
        "width": "260dp",
        "zIndex": 1
    }, {
        "contentAlignment": constants.CONTENT_ALIGN_CENTER,
        "displayText": true,
        "padding": [0, 0, 0, 0],
        "paddingInPixel": false
    }, {});
    var btnStopTorch = new kony.ui.Button({
        "focusSkin": "slButtonGlossRed",
        "height": "50dp",
        "id": "btnStopTorch",
        "isVisible": true,
        "left": "55dp",
        "onClick": AS_Button_967e91b3651c4766baa44e8f9e7bf6c3,
        "skin": "slButtonGlossBlue",
        "text": "stopTorch",
        "top": "152dp",
        "width": "260dp",
        "zIndex": 1
    }, {
        "contentAlignment": constants.CONTENT_ALIGN_CENTER,
        "displayText": true,
        "padding": [0, 0, 0, 0],
        "paddingInPixel": false
    }, {});
    frmCamera.add(btnStartTorch, btnStopTorch);
};

function frmCameraGlobals() {
    frmCamera = new kony.ui.Form2({
        "addWidgets": addWidgetsfrmCamera,
        "enabledForIdleTimeout": false,
        "id": "frmCamera",
        "layoutType": kony.flex.FREE_FORM,
        "needAppMenu": true,
        "skin": "slForm"
    }, {
        "displayOrientation": constants.FORM_DISPLAY_ORIENTATION_PORTRAIT,
        "layoutType": kony.flex.FREE_FORM,
        "padding": [0, 0, 0, 0],
        "paddingInPixel": false
    }, {
        "footerOverlap": false,
        "headerOverlap": false,
        "menuPosition": constants.FORM_MENU_POSITION_AFTER_APPMENU,
        "retainScrollPosition": false,
        "titleBar": true,
        "titleBarSkin": "slTitleBar",
        "windowSoftInputMode": constants.FORM_ADJUST_PAN
    });
    frmCamera.info = {
        "kuid": "de778c42709c4b368fbb9406bd5846cf"
    };
};