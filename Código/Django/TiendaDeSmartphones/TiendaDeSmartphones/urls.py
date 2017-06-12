"""TiendaDeSmartphones URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url, include
from django.contrib import admin
from BaseDeDatos.views import *
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register(r'smartphone/lista', SmartphoneLista)
router.register(r'usuario', UsuarioLista)
router.register(r'venta/lista', VentaLista)
router.register(r'reservacion/lista', ReservacionLista)
router.register(r'traspaso/lista', TraspasoLista)
router.register(r'adquisicion/lista', AdquisicionLista)

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^', include(router.urls)),
    url(r'^inicio/', inicio),
    url(r'^smartphones/', smartphones),
    url(r'^ventas/', ventas),
    url(r'^reservaciones/', reservaciones),
    url(r'^traspasos/', traspasos),
    url(r'^smartphone/eliminar', eliminarSmartphone),
    url(r'^smartphone/guardar', agregarSmartphone),
    url(r'^adquisiciones/',adquisiciones),
    url(r'^adquisicion/guardar', agregarAdquisicion),
    url(r'^adquisicion/eliminar', eliminarAdquisicion),
    url(r'^reservacion/eliminar',eliminarReservacion),
    url(r'^venta/registrar', registrarVenta),
    url(r'^traspaso/registrar', traspaso)
]
