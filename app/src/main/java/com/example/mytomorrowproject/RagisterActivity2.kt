package com.example.mytomorrowproject

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_ragister2.*

class RagisterActivity2 : AppCompatActivity() {
    val TAG: String = "Register"
    var isExistBlank = false
    var isPWSame = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ragister2)
        button_RAGISTER.setOnClickListener {
            Log.d(TAG, "회원가입 버튼")

            val id = edit_NewID.toString()
            val pw = edit_NewPass.toString()
            val pw_re = check_Pass.toString()

            if (id.isEmpty() || pw.isEmpty() || pw_re.isEmpty()) {
                isExistBlank = true
            } else {
                if (pw == pw_re) {
                    isPWSame = true
                }
            }

            if (!isExistBlank && isPWSame) {
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

                val sharedPreference = getSharedPreferences("file name", Context.MODE_PRIVATE)
                val editer = sharedPreference.edit()
                editer.putString("id", id)
                editer.putString("pw", pw)
                editer.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                if(isExistBlank){
                    dialog("blank")
                }
                else if(!isPWSame){
                    dialog("not same")
                }
            }

        }
    }
    fun dialog(type: String){
        val dialog= AlertDialog.Builder(this)

        if (type.equals("blank")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("입력칸을 모두 입력해주세요")
        }
        else if (type.equals("not same")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비밀번호가 같은지 다시 한번 확인해주세요")
        }

        val dialog_listener= object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int){
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->
                        Log.d(TAG,"다이얼로그")
                }
            }
        }
        dialog.setPositiveButton("확인",dialog_listener)
        dialog.show()

    }
}