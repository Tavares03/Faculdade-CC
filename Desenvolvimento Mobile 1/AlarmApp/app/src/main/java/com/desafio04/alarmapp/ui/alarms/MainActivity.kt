package com.desafio04.alarmapp.ui.alarms

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafio04.alarmapp.databinding.ActivityMainBinding
import com.desafio04.alarmapp.ui.add.AddEditAlarmActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AlarmViewModel by viewModels()
    private lateinit var adapter: AlarmsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestNotificationPermission()
        setupRecyclerView()
        setupObservers()
        setupFab()
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1001
            )
        }
    }

    private fun setupRecyclerView() {
        adapter = AlarmsAdapter(
            onToggle = { alarm, enabled -> viewModel.toggleAlarm(alarm, enabled) },
            onEdit = { alarm ->
                val intent = Intent(this, AddEditAlarmActivity::class.java).apply {
                    putExtra(AddEditAlarmActivity.EXTRA_ALARM_ID, alarm.id)
                }
                startActivity(intent)
            },
            onDelete = { alarm ->
                viewModel.deleteAlarm(alarm)
                Snackbar.make(binding.root, "Alarme removido", Snackbar.LENGTH_SHORT).show()
            }
        )
        binding.recyclerAlarms.layoutManager = LinearLayoutManager(this)
        binding.recyclerAlarms.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.allAlarms.observe(this) { alarms ->
            adapter.submitList(alarms)
            binding.layoutEmpty.visibility = if (alarms.isEmpty()) View.VISIBLE else View.GONE
            binding.recyclerAlarms.visibility = if (alarms.isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setupFab() {
        binding.fabAddAlarm.setOnClickListener {
            startActivity(Intent(this, AddEditAlarmActivity::class.java))
        }
    }
}
