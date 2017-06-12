from rest_framework import serializers
from BaseDeDatos.models import *

class AdquisicionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Adquisicion
        fields = '__all__'

class SmartphoneSerializer(serializers.ModelSerializer):
    class Meta:
        model = Smartphone
        fields = '__all__'


class UsuarioSerializer(serializers.ModelSerializer):
    class Meta:
        model = Usuario
        fields = '__all__'


class VentaSerializer(serializers.ModelSerializer):
    idSmartphone =  SmartphoneSerializer()
    idUsuario = UsuarioSerializer()
    class Meta:
        model = Venta
        fields = '__all__'

class ReservacionSerializer(serializers.ModelSerializer):
    idSmartphone =  SmartphoneSerializer()
    idUsuario = UsuarioSerializer()
    class Meta:
        model = Reservacion
        fields = '__all__'

class TraspasoSerializer(serializers.ModelSerializer):
    idSmartphone =  SmartphoneSerializer()
    class Meta:
        model = Traspaso
        fields = '__all__'