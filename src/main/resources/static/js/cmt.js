const cmtBtn = document.querySelector("#cmt-top-btn");
const cmt = document.querySelector("#cmt-write-box");


cmtBtn.addEventListener("click", ()=> {
    if(cmt.style.height == "0px"){
        cmt.style.height = cmt.scrollHeight + "px";
    }else{
        cmt.style.height = "0px";
    }
});