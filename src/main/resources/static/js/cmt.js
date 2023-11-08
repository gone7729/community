







cmtCmtBtn.addEventListener("click", ()=> {
    if(cmtCmt.style.height == "0px"){
        cmtCmt.style.height = cmtCmt.scrollHeight + "px";
    }else{
        cmtCmt.style.height = "0px";
    }
});

