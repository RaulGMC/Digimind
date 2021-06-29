package molina.raul.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.recordatorio.view.*
import molina.raul.digimind.R
import molina.raul.digimind.Recordatorio
import molina.raul.digimind.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var recordatorios = ArrayList<Recordatorio>()
    private var adaptador: AdaptardorTareas? = null

    companion object {
        var tasks = ArrayList<Recordatorio>()
        var first = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if(first){
            fillTasks()
            first = false
        }

        adaptador = AdaptardorTareas(root.context, recordatorios)

        val gridView: GridView = root.findViewById(R.id.grindview)

        gridView.adapter = adaptador

        return root
    }

    fun fillTasks() {
        recordatorios.add(Recordatorio("Practice 1", arrayListOf("Tuesday"), "17:30", ))
        recordatorios.add(Recordatorio("Practice 2", arrayListOf("Monday", "Sunday"), "17:00"))
        recordatorios.add(Recordatorio("Practice 3", arrayListOf("Wednesday"), "14:00"))
        recordatorios.add(Recordatorio("Practice 4", arrayListOf("Saturday"), "11:00"))
        recordatorios.add(Recordatorio("Practice 5", arrayListOf("Friday"), "13:00"))
        recordatorios.add(Recordatorio("Practice 6", arrayListOf("Thursday"), "10:40"))
        recordatorios.add(Recordatorio("Practice 7", arrayListOf("Monday"), "12:00"))
    }
    fun agregar(p: Recordatorio): Boolean {
        return recordatorios.add(p)
    }

    private class AdaptardorTareas : BaseAdapter {
        var tasks = ArrayList<Recordatorio>()
        var contexto: Context? = null

        constructor(contexto: Context, tasks: ArrayList<Recordatorio>) {
            this.contexto = contexto
            this.tasks = tasks
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var task = tasks[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.recordatorio, null)

            vista.textNombreRecordatorio.setText(task.titulo)
            vista.textDiasRecordatorio.setText(task.tiempo)
            vista.textTiempoRecordatorio.setText(task.dias.toString())

            return vista
        }

        override fun getItem(p0: Int): Any {
            return tasks[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }

    }


}