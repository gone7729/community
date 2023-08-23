const cmtBtn = document.querySelector("#cmt-top-btn");
const cmt = document.querySelector("#cmt-write-box");


cmtBtn.addEventListener("click", ()=> {
    if(cmt.style.height == "0px"){
        cmt.style.height = cmt.scrollHeight + "px";
    }else{
        cmt.style.height = "0px";
    }
});

const cmtCmtBtn = document.querySelector(".cmt-main-btn-box >:nth-child(3)");
const cmtCmt = document.querySelector("#cmt-cmt-write-box");


cmtCmtBtn.addEventListener("click", ()=> {
    if(cmtCmt.style.height == "0px"){
        cmtCmt.style.height = cmtCmt.scrollHeight + "px";
    }else{
        cmtCmt.style.height = "0px";
    }
});

