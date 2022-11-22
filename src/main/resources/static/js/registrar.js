


document.getElementById("formRegistroUsuario")?.addEventListener("submit",function (e){
    e.preventDefault();
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;
    datos.direccion = document.getElementById('txtDireccion').value;

    var response = grecaptcha.getResponse();
    if(response.length == 0){
    alert("Please verify you're not a robot");
    return false;
    }
    const request = fetch('api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
      alert("La cuenta fue creada con éxito!");
      window.location.href = '/login'
})



/*async function registrarUsuario() {
    let datos = {};
    datos.nombre = document.getElementbyID('txtNombre').value;
    datos.email = document.getElementbyID('txtEmail').value;
    datos.password = document.getElementbyID('txtPassword').value;
  const request = await fetch('api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });

  const usuarios = await request.text();
}*/