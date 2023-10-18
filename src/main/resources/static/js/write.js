const itemBtn = document.querySelector("#item-btn");
const optionItems = document.querySelectorAll(".option-item");
const itemBox = document.querySelector("#item-box");
const any = document.querySelector(".ql-editor");
const formCategory = document.querySelector("#category");



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


optionItems.forEach((item) => {
              item.addEventListener("click", (e) => {
                itemBtn.firstElementChild.textContent = item.textContent;
                itemBox.style.height = "0px";
                formCategory.value = e.target.value;
              });
            });

function set(){
    let content = document.querySelector("#content-mid");
    let text = document.querySelector(".ql-editor").innerHTML;

    content.value = text;
}





