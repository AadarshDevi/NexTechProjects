const button = document.getElementById("replace-text-button");
const replace_text = document.getElementById("js-text-switch");
let text_swap = false;

function my_function() {
  if (!text_swap) {
    text_swap = true;
    replace_text.textContent = "Hello User, Welcome to DemoPage.";
    button.textContent = "Reset Text";
  } else {
    text_swap = false;
    replace_text.textContent = "This is a NexTech DemoSite.";
    button.textContent = "Replace Text in Red Border";
  }
}

button.addEventListener("click", my_function);
