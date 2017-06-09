from django.shortcuts import redirect
from django.shortcuts import render_to_response

from BaseDeDatos.serializers import *
from rest_framework import viewsets

####################################################################
def inicio(request):
    return render_to_response("paginaInicio.html")

def smartphones(request):
    return render_to_response("paginaSmartphones.html")

def ventas(request):
    return render_to_response("paginaVentas.html")

def traspasos(request):
    return render_to_response("paginaTraspasos.html")

def reservaciones(request):
    return render_to_response("paginaReservaciones.html")
#####################################################################
def eliminarSmartphone(request):
    if request.POST:
        smartphone = Smartphone.objects.get(idSmartphone=request.POST['id'])
        smartphone.delete()
    return render_to_response("paginaSmartphones.html")

def agregarSmartphone(request):
    if request.POST:
        smartphone = Smartphone()
        if request.POST['idSmartphone'] != '':
            smartphone.idSmartphone = request.POST['idSmartphone']

        smartphone.marca = request.POST['marca']
        smartphone.modelo = request.POST['modelo']
        smartphone.descripcion = request.POST['descripcion']
        smartphone.color = request.POST['color']
        smartphone.precio = request.POST['precio']
        smartphone.cantidad = request.POST['cantidad']
        smartphone.save()
    return render_to_response("paginaSmartphones.html")

#####################################################################
class SmartphoneLista(viewsets.ModelViewSet):
    queryset = Smartphone.objects.all();
    serializer_class = SmartphoneSerializer

class UsuarioLista(viewsets.ModelViewSet):
    queryset = Usuario.objects.all()
    serializer_class = UsuarioSerializer

class VentaLista(viewsets.ModelViewSet):
    queryset = Venta.objects.all()
    serializer_class = VentaSerializer

class TraspasoLista(viewsets.ModelViewSet):
    queryset = Traspaso.objects.all()
    serializer_class = TraspasoSerializer

class ReservacionLista(viewsets.ModelViewSet):
    queryset = Reservacion.objects.all()
    serializer_class = ReservacionSerializer