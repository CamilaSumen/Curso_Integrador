fetch('api/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({email:"camila@gmail.com",password:"123456"})
  }).then((res)=>{
    res.text().then((text)=>{console.log(text)})
  })


document.getElementById('formInicioSesion').addEventListener("submit",function (e){
  e.preventDefault();
  let datos = {};
  datos.email = document.getElementById('txtCorreo').value;
  datos.password = document.getElementById('txtPassword').value;

  const request = fetch('api/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  }).then((response)=>{
    response.text().then((resultado)=>{
        if (resultado != 'FAIL') {
            window.location.href = '/carta'
          } else {
            alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
          }
    })
  });
  })
/*
function iniciarSesion(e) {
  e.preventDefault();
  let datos = {};
  datos.email = document.getElementById('txtCorreo').value;
  datos.password = document.getElementById('txtPassword').value;

  const request = fetch('api/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  }).then((response)=>{
    response.text().then((resultado)=>{
        if (resultado != 'FAIL') {
            window.location.href = 'ordena.html'
          } else {
            alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
          }
    })
  });
}
*/