// active class in selected menu list items
let list = document.querySelectorAll('.dashboard-menu-items');
for(let i=0;i<list.length;i++)
{
    list[i].onclick = function()
    {
        let j=0;
        while(j<list.length)
        {
            list[j++].className = 'dashboard-menu-items';
        }
        list[i].className = 'dashboard-menu-items toggle-active hovered';
    }
}

// dashboard-toggle
let dashToggle = document.querySelector('.side-dashboard-toggle');
let nav = document.querySelector('.dashboard');
dashToggle.onclick = function()
{
    dashToggle.classList.toggle('toggle-active');
    nav.classList.toggle('toggle-active');
}




