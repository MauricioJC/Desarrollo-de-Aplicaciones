from __future__ import unicode_literals
from django.db import models



class Adquisicion(models.Model):
    idAdquisicion = models.AutoField(primary_key=True)
    producto = models.CharField(max_length=200)
    proveedor = models.CharField(max_length=100)
    precio = models.IntegerField()
    cantidad = models.IntegerField()
    fecha = models.DateField()

    class Meta:
        managed = False
        db_table = 'adquisicion'

class Reservacion(models.Model):
    idReservacion = models.AutoField(primary_key=True)
    idSmartphone = models.ForeignKey('Smartphone', models.DO_NOTHING, db_column='idSmartphone')
    idUsuario = models.ForeignKey('Usuario', models.DO_NOTHING, db_column='idUsuario')
    fecha = models.DateField()

    class Meta:
        managed = False
        db_table = 'reservacion'

class Smartphone(models.Model):
    idSmartphone = models.AutoField(primary_key=True)
    marca = models.CharField(max_length=60)
    modelo = models.CharField(max_length=60)
    descripcion = models.CharField(max_length=100)
    color = models.CharField(max_length=60)
    precio = models.IntegerField()
    cantidad = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'smartphone'

class Traspaso(models.Model):
    idTraspaso = models.AutoField(primary_key=True)
    idSmartphone = models.ForeignKey(Smartphone, models.DO_NOTHING, db_column='idSmartphone')
    tipo = models.CharField(max_length=40)
    fecha = models.DateField()

    class Meta:
        managed = False
        db_table = 'traspaso'

class Usuario(models.Model):
    idUsuario = models.AutoField(primary_key=True)
    correo = models.CharField(max_length=60)
    contraseña = models.CharField(max_length=60)

    class Meta:
        managed = False
        db_table = 'usuario'

class Venta(models.Model):
    idVenta = models.AutoField(primary_key=True)
    idSmartphone = models.ForeignKey(Smartphone, models.DO_NOTHING, db_column='idSmartphone')
    idUsuario = models.ForeignKey(Usuario, models.DO_NOTHING, db_column='idUsuario')
    fecha = models.DateField()

    class Meta:
        managed = False
        db_table = 'venta'
