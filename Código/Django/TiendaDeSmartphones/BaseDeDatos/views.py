from django.shortcuts import redirect
from django.shortcuts import render_to_response
import datetime
from BaseDeDatos.serializers import *
from rest_framework import viewsets


from django.http import JsonResponse
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

def adquisiciones(request):
    return render_to_response("paginaAdquisiciones.html")

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


def agregarAdquisicion(request):
    if request.POST:
        adquisicion = Adquisicion()
        adquisicion.producto = request.POST['producto']
        adquisicion.proveedor = request.POST['proveedor']
        adquisicion.precio = request.POST['precio']
        adquisicion.cantidad = request.POST['cantidad']
        adquisicion.fecha = datetime.datetime.now()
        adquisicion.save()
    return render_to_response("paginaAdquisiciones.html")

def eliminarAdquisicion(request):
    if request.POST:
        adquisicion = Adquisicion.objects.get(idAdquisicion=request.POST['id'])
        adquisicion.delete()
    return render_to_response("paginaAdquisiciones.html")

def eliminarReservacion(request):
    if request.POST:
        reservacion = Reservacion.objects.get(idReservacion=request.POST['id'])
        reservacion.delete()
    return render_to_response("paginaReservaciones.html")


def registrarVenta(request):
    if request.POST:
        venta = Venta()
        smartphone = Smartphone.objects.get(idSmartphone=request.POST['idSmartphone'])
        venta.idSmartphone = smartphone
        smartphone.cantidad = smartphone.cantidad-1
        smartphone.save()
        venta.fecha = datetime.datetime.now()

        if request.POST['usuario']!='':
            venta.idUsuario = Usuario.objects.get(idUsuario=request.POST['usuario'])

        venta.save()

    return render_to_response("paginaVentas.html")


def traspaso(request):
    if request.POST:
        smartphone = Smartphone.objects.get(idSmartphone=request.POST['id'])
        smartphone.cantidad = smartphone.cantidad + 1
        smartphone.save()
        newTraspaso = Traspaso()
        newTraspaso.idSmartphone = smartphone
        newTraspaso.fecha = datetime.datetime.now()
        newTraspaso.tipo = 'Recibido'
        newTraspaso.save()
    return render_to_response("paginaInicio.html")

#####################################################################
class AdquisicionLista(viewsets.ModelViewSet):
    queryset = Adquisicion.objects.all()
    serializer_class = AdquisicionSerializer

class SmartphoneLista(viewsets.ModelViewSet):
    queryset = Smartphone.objects.all()
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