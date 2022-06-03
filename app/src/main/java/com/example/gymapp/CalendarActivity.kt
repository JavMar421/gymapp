package com.example.gymapp


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gymapp.UserApplication.Companion.datos
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CalendarActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        val database = Firebase.database("https://gym-proyect-dam-default-rtdb.europe-west1.firebasedatabase.app")
        val logout = findViewById<ImageView>(R.id.logoutlogo)
        val edittable = findViewById<ImageView>(R.id.tablaicon)
        val editejer = findViewById<ImageView>(R.id.ejericon)
        val l9 = findViewById<TextView>(R.id.L9)
        val l10 = findViewById<TextView>(R.id.L10)
        val l11 = findViewById<TextView>(R.id.L11)
        val l12 = findViewById<TextView>(R.id.L12)
        val l13 = findViewById<TextView>(R.id.L13)
        val l17 = findViewById<TextView>(R.id.L17)
        val l18 = findViewById<TextView>(R.id.L18)
        val l19 = findViewById<TextView>(R.id.L19)
        val l20 = findViewById<TextView>(R.id.L20)
        val m9 = findViewById<TextView>(R.id.M9)
        val m10 = findViewById<TextView>(R.id.M10)
        val m11 = findViewById<TextView>(R.id.M11)
        val m12 = findViewById<TextView>(R.id.M12)
        val m13 = findViewById<TextView>(R.id.M13)
        val m17 = findViewById<TextView>(R.id.M17)
        val m18 = findViewById<TextView>(R.id.M18)
        val m19 = findViewById<TextView>(R.id.M19)
        val m20 = findViewById<TextView>(R.id.M20)
        val x9 = findViewById<TextView>(R.id.X9)
        val x10 = findViewById<TextView>(R.id.X10)
        val x11 = findViewById<TextView>(R.id.X11)
        val x12 = findViewById<TextView>(R.id.X12)
        val x13 = findViewById<TextView>(R.id.X13)
        val x17 = findViewById<TextView>(R.id.X17)
        val x18 = findViewById<TextView>(R.id.X18)
        val x19 = findViewById<TextView>(R.id.X19)
        val x20 = findViewById<TextView>(R.id.X20)
        val j9 = findViewById<TextView>(R.id.J9)
        val j10 = findViewById<TextView>(R.id.J10)
        val j11 = findViewById<TextView>(R.id.J11)
        val j12 = findViewById<TextView>(R.id.J12)
        val j13 = findViewById<TextView>(R.id.J13)
        val j17 = findViewById<TextView>(R.id.J17)
        val j18 = findViewById<TextView>(R.id.J18)
        val j19 = findViewById<TextView>(R.id.J19)
        val j20 = findViewById<TextView>(R.id.J20)
        val v9 = findViewById<TextView>(R.id.V9)
        val v10 = findViewById<TextView>(R.id.V10)
        val v11 = findViewById<TextView>(R.id.V11)
        val v12 = findViewById<TextView>(R.id.V12)
        val v13 = findViewById<TextView>(R.id.V13)
        val v17 = findViewById<TextView>(R.id.V17)
        val v18 = findViewById<TextView>(R.id.V18)
        val v19 = findViewById<TextView>(R.id.V19)
        val v20 = findViewById<TextView>(R.id.V20)
        val borrar = findViewById<TextView>(R.id.borrarevento)
        val evento = findViewById<EditText>(R.id.evento)
        val dia = findViewById<EditText>(R.id.Dia)
        val hora = findViewById<EditText>(R.id.horaNewEv)
        val resumenevento = findViewById<EditText>(R.id.resumenevento)
        val personas = findViewById<EditText>(R.id.personas)
        val reservar = findViewById<TextView>(R.id.eventobotton)
        val botonesevento = findViewById<LinearLayout>(R.id.botonesevento)
        val horas: MutableList<String> = mutableListOf("9","10","11","12","13","17","18","19","20")
        val dias: MutableList<String> = mutableListOf("lunes","martes","miercoles","jueves","viernes")
        var numpersonas:Int
        logout.setOnClickListener {
            datos.wipe()
            Toast.makeText(this, "$saveuser ha Cerrado la Sesión", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        edittable.setOnClickListener {
            val intent: Intent
            if (adminmode) {
                intent = Intent(this, TableCreatorV2::class.java)
            } else {
                editejer.visibility = View.INVISIBLE
                intent = Intent(this, TableActivityV2::class.java)
            }
            startActivity(intent)
        }
        editejer.setOnClickListener {
            val intent = Intent(this, EjerCreator::class.java)
            startActivity(intent)
        }
        dia.visibility=View.INVISIBLE
        hora.visibility=View.INVISIBLE
        if (adminmode) {
            botonesevento.visibility=View.VISIBLE
            evento.isEnabled = true
            resumenevento.isEnabled = true
            personas.isEnabled = true
            reservar.text = "Guardar"
        }else {
            borrar.visibility = View.INVISIBLE
            editejer.visibility = View.INVISIBLE
            dia.visibility=View.INVISIBLE
            hora.visibility=View.INVISIBLE
            evento.isEnabled = false
            resumenevento.isEnabled = false
            personas.isEnabled = false
        }

        borrar.setOnClickListener{
            Toast.makeText(this, evento.text.toString()+" Borrado", Toast.LENGTH_SHORT).show()
            database.reference.child("horario").child(dia.text.toString().lowercase()).child(hora.text.toString()).removeValue()
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        reservar.setOnClickListener {
            if (adminmode) {
            if (horas.contains(hora.text.toString()) && dias.contains(dia.text.toString().lowercase()) && evento.text.toString().isNotEmpty() && personas.text.toString().isNotEmpty() && resumenevento.text.toString().isNotEmpty()){

                numpersonas=Integer.parseInt(personas.text.toString())
                Toast.makeText(this, "dia y hora correcta", Toast.LENGTH_SHORT).show()

                    Toast.makeText(this, evento.text.toString() + " Editado", Toast.LENGTH_SHORT).show()
                    database.reference.child("horario").child(dia.text.toString().lowercase()).child(hora.text.toString()).child("nombre").setValue(evento.text.toString())
                    database.reference.child("horario").child(dia.text.toString().lowercase()).child(hora.text.toString()).child("resumen").setValue(resumenevento.text.toString())
                    database.reference.child("horario").child(dia.text.toString().lowercase()).child(hora.text.toString()).child("personas").setValue(numpersonas)

                    val intent = Intent(this, CalendarActivity::class.java)
                    startActivity(intent)
                } else{Toast.makeText(this, "datos incorrectos", Toast.LENGTH_SHORT).show()}}
            else {
                if (evento.text.isNotEmpty()){
                    database.reference.child("horario").child(dia.text.toString().lowercase())
                        .child(hora.text.toString()).child("personas").get().addOnSuccessListener {

                        if (Integer.parseInt(it.value.toString()) < 20) {
                            database.reference.child("horario")
                                .child(dia.text.toString().lowercase()).child(hora.text.toString())
                                .child("personas").setValue((Integer.parseInt(it.value.toString()) + 1))
                            Toast.makeText(
                                this,
                                evento.text.toString() + " Reservado",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, CalendarActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                evento.text.toString() + " está lleno",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
            }

        }

        }

         l9.setOnClickListener {
            dia.setText(dias[0])
            hora.setText(horas[0])
            database.reference.child("horario").child(dias[0]).child(horas[0]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[0]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[0]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        l10.setOnClickListener {
            dia.setText(dias[0])
            hora.setText(horas[1])
            database.reference.child("horario").child(dias[0]).child(horas[1]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[1]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[1]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        l11.setOnClickListener {
            dia.setText(dias[0])
            hora.setText(horas[2])
            database.reference.child("horario").child(dias[0]).child(horas[2]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[2]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[2]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        l12.setOnClickListener {
            dia.setText(dias[0])
            hora.setText(horas[3])
            database.reference.child("horario").child(dias[0]).child(horas[3]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[3]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[3]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        l13.setOnClickListener {
            dia.setText(dias[0])
            hora.setText(horas[4])
            database.reference.child("horario").child(dias[0]).child(horas[4]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[4]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[4]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        l17.setOnClickListener {
            dia.setText(dias[0])
            hora.setText(horas[5])
            database.reference.child("horario").child(dias[0]).child(horas[5]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[5]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[5]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        l18.setOnClickListener {
            dia.setText(dias[0])
            hora.setText(horas[6])
            database.reference.child("horario").child(dias[0]).child(horas[6]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[6]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[6]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        l19.setOnClickListener {
            dia.setText(dias[0])
            hora.setText(horas[7])
            database.reference.child("horario").child(dias[0]).child(horas[7]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")

                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[7]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[7]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        l20.setOnClickListener {
            dia.setText(dias[0])
            hora.setText(horas[8])
            database.reference.child("horario").child(dias[0]).child(horas[8]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[8]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[0]).child(horas[8]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }

         m9.setOnClickListener {
            dia.setText(dias[1])
            hora.setText(horas[0])
            database.reference.child("horario").child(dias[1]).child(horas[0]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[0]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[0]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        m10.setOnClickListener {
            dia.setText(dias[1])
            hora.setText(horas[1])
            database.reference.child("horario").child(dias[1]).child(horas[1]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[1]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[1]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        m11.setOnClickListener {
            dia.setText(dias[1])
            hora.setText(horas[2])
            database.reference.child("horario").child(dias[1]).child(horas[2]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[2]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[2]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        m12.setOnClickListener {
            dia.setText(dias[1])
            hora.setText(horas[3])
            database.reference.child("horario").child(dias[1]).child(horas[3]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[3]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[3]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        m13.setOnClickListener {
            dia.setText(dias[1])
            hora.setText(horas[4])
            database.reference.child("horario").child(dias[1]).child(horas[4]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[4]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[4]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        m17.setOnClickListener {
            dia.setText(dias[1])
            hora.setText(horas[5])
            database.reference.child("horario").child(dias[1]).child(horas[5]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[5]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[5]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        m18.setOnClickListener {
            dia.setText(dias[1])
            hora.setText(horas[6])
            database.reference.child("horario").child(dias[1]).child(horas[6]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[6]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[6]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        m19.setOnClickListener {
            dia.setText(dias[1])
            hora.setText(horas[7])
            database.reference.child("horario").child(dias[1]).child(horas[7]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")

                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[7]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[7]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        m20.setOnClickListener {
            dia.setText(dias[1])
            hora.setText(horas[8])
            database.reference.child("horario").child(dias[1]).child(horas[8]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[8]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[1]).child(horas[8]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }

         x9.setOnClickListener {
            dia.setText(dias[2])
            hora.setText(horas[0])
            database.reference.child("horario").child(dias[2]).child(horas[0]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[0]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[0]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        x10.setOnClickListener {
            dia.setText(dias[2])
            hora.setText(horas[1])
            database.reference.child("horario").child(dias[2]).child(horas[1]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[1]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[1]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        x11.setOnClickListener {
            dia.setText(dias[2])
            hora.setText(horas[2])
            database.reference.child("horario").child(dias[2]).child(horas[2]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[2]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[2]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        x12.setOnClickListener {
            dia.setText(dias[2])
            hora.setText(horas[3])
            database.reference.child("horario").child(dias[2]).child(horas[3]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[3]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[3]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        x13.setOnClickListener {
            dia.setText(dias[2])
            hora.setText(horas[4])
            database.reference.child("horario").child(dias[2]).child(horas[4]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[4]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[4]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        x17.setOnClickListener {
            dia.setText(dias[2])
            hora.setText(horas[5])
            database.reference.child("horario").child(dias[2]).child(horas[5]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[5]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[5]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        x18.setOnClickListener {
            dia.setText(dias[2])
            hora.setText(horas[6])
            database.reference.child("horario").child(dias[2]).child(horas[6]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[6]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[6]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        x19.setOnClickListener {
            dia.setText(dias[2])
            hora.setText(horas[7])
            database.reference.child("horario").child(dias[2]).child(horas[7]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")

                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[7]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[7]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        x20.setOnClickListener {
            dia.setText(dias[2])
            hora.setText(horas[8])
            database.reference.child("horario").child(dias[2]).child(horas[8]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[8]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[2]).child(horas[8]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }

         j9.setOnClickListener {
            dia.setText(dias[3])
            hora.setText(horas[0])
            database.reference.child("horario").child(dias[3]).child(horas[0]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[0]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[0]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        j10.setOnClickListener {
            dia.setText(dias[3])
            hora.setText(horas[1])
            database.reference.child("horario").child(dias[3]).child(horas[1]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[1]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[1]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        j11.setOnClickListener {
            dia.setText(dias[3])
            hora.setText(horas[2])
            database.reference.child("horario").child(dias[3]).child(horas[2]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[2]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[2]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        j12.setOnClickListener {
            dia.setText(dias[3])
            hora.setText(horas[3])
            database.reference.child("horario").child(dias[3]).child(horas[3]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[3]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[3]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        j13.setOnClickListener {
            dia.setText(dias[3])
            hora.setText(horas[4])
            database.reference.child("horario").child(dias[3]).child(horas[4]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[4]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[4]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        j17.setOnClickListener {
            dia.setText(dias[3])
            hora.setText(horas[5])
            database.reference.child("horario").child(dias[3]).child(horas[5]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[5]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[5]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        j18.setOnClickListener {
            dia.setText(dias[3])
            hora.setText(horas[6])
            database.reference.child("horario").child(dias[3]).child(horas[6]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[6]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[6]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        j19.setOnClickListener {
            dia.setText(dias[3])
            hora.setText(horas[7])
            database.reference.child("horario").child(dias[3]).child(horas[7]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")

                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[7]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[7]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        j20.setOnClickListener {
            dia.setText(dias[3])
            hora.setText(horas[8])
            database.reference.child("horario").child(dias[3]).child(horas[8]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[8]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[3]).child(horas[8]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }

         v9.setOnClickListener {
            dia.setText(dias[4])
            hora.setText(horas[0])
            database.reference.child("horario").child(dias[4]).child(horas[0]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[0]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[0]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        v10.setOnClickListener {
            dia.setText(dias[4])
            hora.setText(horas[1])
            database.reference.child("horario").child(dias[4]).child(horas[1]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[1]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[1]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        v11.setOnClickListener {
            dia.setText(dias[4])
            hora.setText(horas[2])
            database.reference.child("horario").child(dias[4]).child(horas[2]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[2]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[2]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        v12.setOnClickListener {
            dia.setText(dias[4])
            hora.setText(horas[3])
            database.reference.child("horario").child(dias[4]).child(horas[3]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[3]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[3]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        v13.setOnClickListener {
            dia.setText(dias[4])
            hora.setText(horas[4])
            database.reference.child("horario").child(dias[4]).child(horas[4]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[4]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[4]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        v17.setOnClickListener {
            dia.setText(dias[4])
            hora.setText(horas[5])
            database.reference.child("horario").child(dias[4]).child(horas[5]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[5]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[5]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        v18.setOnClickListener {
            dia.setText(dias[4])
            hora.setText(horas[6])
            database.reference.child("horario").child(dias[4]).child(horas[6]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint = "No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[6]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[6]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        v19.setOnClickListener {
            dia.setText(dias[4])
            hora.setText(horas[7])
            database.reference.child("horario").child(dias[4]).child(horas[7]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")

                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[7]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[7]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }
        v20.setOnClickListener {
            dia.setText(dias[4])
            hora.setText(horas[8])
            database.reference.child("horario").child(dias[4]).child(horas[8]).child("nombre").get().addOnSuccessListener {
                if (it.value==null){
                    evento.hint="No hay Evento"
                    evento.setText("")
                }
                else{
                    evento.setText(it.value.toString())

                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[8]).child("resumen").get().addOnSuccessListener {
                if (it.value==null){
                    resumenevento.setText("")
                }
                else{
                    resumenevento.setText(it.value.toString())
                }
            }
            database.reference.child("horario").child(dias[4]).child(horas[8]).child("personas").get().addOnSuccessListener {
                if (it.value==null){
                    personas.setText("")
                }
                else{
                    personas.setText(it.value.toString())
                }
            }
        }


        database.reference.child("horario").child(dias[0]).child(horas[0]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                l9.text=""
            }
            else{
                l9.text=it.value.toString()
                l9.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[0]).child(horas[1]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                l10.text=""
            }
            else{
                l10.text=it.value.toString()
                l10.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[0]).child(horas[2]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                l11.text=""
            }
            else{
                l11.text=it.value.toString()
                l11.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[0]).child(horas[3]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                l12.text=""
            }
            else{
                l12.text=it.value.toString()
                l12.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[0]).child(horas[4]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                l13.text=""
            }
            else{
                l13.text=it.value.toString()
                l13.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[0]).child(horas[5]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                l17.text=""
            }
            else{
                l17.text=it.value.toString()
                l17.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[0]).child(horas[6]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                l18.text=""
            }
            else{
                l18.text=it.value.toString()
                l18.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[0]).child(horas[7]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                l19.text=""
            }
            else{
                l19.text=it.value.toString()
                l19.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[0]).child(horas[8]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                l20.text=""
            }
            else{
                l20.text=it.value.toString()
                l20.background.setTint(Color.parseColor("#EC6767"))
            }
        }

        database.reference.child("horario").child(dias[1]).child(horas[0]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                m9.text=""
            }
            else{
                m9.text=it.value.toString()
                m9.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[1]).child(horas[1]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                m10.text=""
            }
            else{
                m10.text=it.value.toString()
                m10.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[1]).child(horas[2]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                m11.text=""
            }
            else{
                m11.text=it.value.toString()
                m11.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[1]).child(horas[3]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                m12.text=""
            }
            else{
                m12.text=it.value.toString()
                m12.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[1]).child(horas[4]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                m13.text=""
            }
            else{
                m13.text=it.value.toString()
                m13.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[1]).child(horas[5]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                m17.text=""
            }
            else{
                m17.text=it.value.toString()
                m17.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[1]).child(horas[6]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                m18.text=""
            }
            else{
                m18.text=it.value.toString()
                m18.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[1]).child(horas[7]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                m19.text=""
            }
            else{
                m19.text=it.value.toString()
                m19.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[1]).child(horas[8]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                m20.text=""
            }
            else{
                m20.text=it.value.toString()
                m20.background.setTint(Color.parseColor("#EC6767"))
            }
        }

        database.reference.child("horario").child(dias[2]).child(horas[0]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                x9.text=""
            }
            else{
                x9.text=it.value.toString()
                x9.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[2]).child(horas[1]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                x10.text=""
            }
            else{
                x10.text=it.value.toString()
                x10.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[2]).child(horas[2]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                x11.text=""
            }
            else{
                x11.text=it.value.toString()
                x11.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[2]).child(horas[3]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                x12.text=""
            }
            else{
                x12.text=it.value.toString()
                x12.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[2]).child(horas[4]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                x13.text=""
            }
            else{
                x13.text=it.value.toString()
                x13.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[2]).child(horas[5]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                x17.text=""
            }
            else{
                x17.text=it.value.toString()
                x17.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[2]).child(horas[6]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                x18.text=""
            }
            else{
                x18.text=it.value.toString()
                x18.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[2]).child(horas[7]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                x19.text=""
            }
            else{
                x19.text=it.value.toString()
                x19.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[2]).child(horas[8]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                x20.text=""
            }
            else{
                x20.text=it.value.toString()
                x20.background.setTint(Color.parseColor("#EC6767"))
            }
        }

        database.reference.child("horario").child(dias[3]).child(horas[0]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                j9.text=""
            }
            else{
                j9.text=it.value.toString()
                j9.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[3]).child(horas[1]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                j10.text=""
            }
            else{
                j10.text=it.value.toString()
                j10.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[3]).child(horas[2]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                j11.text=""
            }
            else{
                j11.text=it.value.toString()
                j11.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[3]).child(horas[3]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                j12.text=""
            }
            else{
                j12.text=it.value.toString()
                j12.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[3]).child(horas[4]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                j13.text=""
            }
            else{
                j13.text=it.value.toString()
                j13.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[3]).child(horas[5]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                j17.text=""
            }
            else{
                j17.text=it.value.toString()
                j17.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[3]).child(horas[6]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                j18.text=""
            }
            else{
                j18.text=it.value.toString()
                j18.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[3]).child(horas[7]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                j19.text=""
            }
            else{
                j19.text=it.value.toString()
                j19.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[3]).child(horas[8]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                j20.text=""
            }
            else{
                j20.text=it.value.toString()
                j20.background.setTint(Color.parseColor("#EC6767"))
            }
        }

        database.reference.child("horario").child(dias[4]).child(horas[0]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                v9.text=""
            }
            else{
                v9.text=it.value.toString()
                v10.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[4]).child(horas[1]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                v10.text=""
            }
            else{
                v10.text=it.value.toString()
                v10.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[4]).child(horas[2]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                v11.text=""
            }
            else{
                v11.text=it.value.toString()
                v11.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[4]).child(horas[3]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                v12.text=""
            }
            else{
                v12.text=it.value.toString()
                v12.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[4]).child(horas[4]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                v13.text=""
            }
            else{
                v13.text=it.value.toString()
                v13.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[4]).child(horas[5]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                v17.text=""
            }
            else{
                v17.text=it.value.toString()
                v17.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[4]).child(horas[6]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                v18.text=""
            }
            else{
                v18.text=it.value.toString()
                v18.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[4]).child(horas[7]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                v19.text=""
            }
            else{
                v19.text=it.value.toString()
                v19.background.setTint(Color.parseColor("#EC6767"))
            }
        }
        database.reference.child("horario").child(dias[4]).child(horas[8]).child("nombre").get().addOnSuccessListener {
            if (it.value==null){
                v20.text=""
            }
            else{
                v20.text=it.value.toString()
                v20.background.setTint(Color.parseColor("#EC6767"))
            }
        }
    }
}