

document.getElementById("formContacto")?.addEventListener("submit",function (ex){
    ex.preventDefault();
    let datosr = {};
    datosr.nombre = document.getElementById('txtNombre').value;
    datosr.correo = document.getElementById('txtCorreo').value;
    datosr.telefono = document.getElementById('txtTelefono').value;
    datosr.mensaje = document.getElementById('txtMensaje').value;


    const request = fetch('api/contactos', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datosr)
  });
   alert("Tu solicitud fue enviada");
      window.location.href = 'escribenos.html'
})