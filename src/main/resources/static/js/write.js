const itemBtn = document.querySelector("#item-btn");
const optionItems = document.querySelectorAll(".option-item");
const itemBox = document.querySelector("#item-box");
const any = document.querySelector(".ql-editor");



itemBtn.addEventListener("click", ()=> {
    if(itemBox.style.height == "0px"){
        itemBox.style. height = itemBox.scrollHeight + "px";
    }else{
        itemBox.style. height = "0px";
    }
});

optionItems.forEach((item) => {
  item.addEventListener("click", () => {
    itemBtn.firstElementChild.textContent = item.textContent;
    itemBox.style. height = "0px";
  });
});

any.addEventListener("click", ()=>{
    if(itemBox.style.height != "0px"){
        itemBox.style. height = "0px";
    }
})




let liv = "";

optionItems.forEach((item) => {
        item.addEventListener("click", () => {
            liv = item.value;
            });
        });
liv;

function set(){
    let content = document.querySelector("#content-mid");
    let text = document.querySelector(".ql-editor").innerHTML;
    let category = document.querySelector("#category");

    category.value = liv;
    content.value = text;
}





