package com.example.peminjaman_buku

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.peminjaman_buku.R

class MainActivity : AppCompatActivity() {
    private val bookList = mutableListOf("Kotlin for Beginners", "Android Development", "Data Structures")
    private val borrowedBooks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)
        val borrowButton: Button = findViewById(R.id.borrowButton)
        val addBookButton: Button = findViewById(R.id.addBookButton)
        val bookInput: EditText = findViewById(R.id.bookInput)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bookList)
        listView.adapter = adapter

        addBookButton.setOnClickListener {
            val bookName = bookInput.text.toString().trim()
            if (bookName.isNotEmpty()) {
                bookList.add(bookName)
                adapter.notifyDataSetChanged()
                bookInput.text.clear()
            } else {
                Toast.makeText(this, "Masukkan nama buku!", Toast.LENGTH_SHORT).show()
            }
        }

        borrowButton.setOnClickListener {
            val position = listView.checkedItemPosition
            if (position != ListView.INVALID_POSITION && position < bookList.size) {
                val book = bookList.removeAt(position)
                borrowedBooks.add(book)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "$book dipinjam", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pilih buku untuk dipinjam!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
