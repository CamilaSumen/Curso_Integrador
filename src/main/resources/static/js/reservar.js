

document.getElementById("formReservar")?.addEventListener("submit",function (ex){
    ex.preventDefault();
    let datosr = {};
    datosr.sede = document.getElementById('txtSede').value;
    datosr.nombre = document.getElementById('txtNombre').value;
    datosr.apellido = document.getElementById('txtApellido').value;
    datosr.correo = document.getElementById('txtEmail').value;
    datosr.telefono = document.getElementById('txtTelefono').value;
    datosr.fecha = document.getElementById('txtFecha').value;
    datosr.hora = document.getElementById('txtHora').value;
    datosr.cantidad = document.getElementById('txtCantidad').value;
    datosr.comentario = document.getElementById('txtMensaje').value;


    const request = fetch('api/reservas', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datosr)
  });
   alert("Reserva registrada");
      window.location.href = 'reserva.html'
})