package molina.raul.digimind
import java.io.Serializable

data class Recordatorio(var titulo : String,
                        var dias: ArrayList<String>,
                        var tiempo: String,
                   ): Serializable
