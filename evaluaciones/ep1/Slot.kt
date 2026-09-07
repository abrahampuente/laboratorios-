class Slot(val id: Int){
    var estado: EstadoSlot = EstadoSlot.LIBRE
      private set

    var bicicleta: Bicicleta? = null
      private set

    var motivo: String? = null
      private set

    fun arrendar(bicicleta: Bicicleta){
        this.bicicleta = bicicleta
        this.estado = EstadoSlot.ARRENDADA
        this.motivo = null
    }

    fun liberar(){
        this.bicicleta = null
        this.estado = EstadoSlot.LIBRE
        this.motivo = null
    }

    fun proceso(motivo: String){
        this.estado = EstadoSlot.PROCESO
        this.motivo = motivo
    }

    fun mantencion(motivo: String){
        this.estado = EstadoSlot.MANTENCION
        this.motivo = motivo
    }
}
