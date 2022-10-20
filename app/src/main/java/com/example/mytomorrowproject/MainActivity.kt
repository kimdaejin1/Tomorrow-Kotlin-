package com.example.mytomorrowproject

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : AppCompatActivity
    val TAG: String="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_LOGIN.setOnClickListener{
            var id=edit_ID.text.toString()
            var pw=edit_PASS.text.toString()

            val sharedPreferences=getSharedPreferences("file name", Context.MODE_PRIVATE)
            val savedId=sharedPreferences.getString("id","")
            val savePass=sharedPreferences.getString("pass","")

            if(id==savedId&&pw==savePass){
                dialog("success")
                val intent =Intent(this,DaialryActivity::class.java)
                startActivity(intent)
            }
            else{
                dialog("fail")
            }
        }

        button_RAGISTER.setOnClickListener {
            val intent = Intent(this,RagisterActivity2::class.java)
            startActivity(intent)
            Log.d("TAG", "sss")
        }
    }
    private fun dialog(type: String){
        var dialog= AlertDialog.Builder(this)

        if (type == "success") {
            dialog.setTitle("로그인 성공")
            dialog.setMessage("로그인 성공!!")
        }
        else if(type == "fail"){
            dialog.setTitle("로그인 실패")
            dialog.setMessage("아이디와 비밀번호를 확인해주세요")
        }

        var dialog_listener= DialogInterface.OnClickListener { _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE->
                    Log.d(TAG,"")
            }
        }
        dialog.setPositiveButton("확인",dialog_listener)
        dialog.show()
    }
}