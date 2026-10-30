package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Écran principal
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    ProfileCard(
        name = "Elio Dikoume",
        role = "Développeur fullstack junior",
        description = "Étudiant passionné par le dev, je découvre Jetpack Compose.",
        skills = listOf("Kotlin", "Jetpack Compose", "Git"),
        isAvailable = true,
        onContactClick = { /* Plus tard : ouvrir un écran de contact */ },
        onAvailabilityClick = { /* Plus tard : afficher les détails */ },
        // padding externe
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    )
}

// La fiche
@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    isAvailable: Boolean,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = Color.LightGray, modifier = modifier) {
        // padding interne
        Column(modifier = Modifier.padding(16.dp)) {
            ProfileHeader(
                name = name,
                role = role,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = description,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            SkillList(
                skills = skills,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            AvailabilityInfo(
                isAvailable = isAvailable,
                onClick = onAvailabilityClick,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            ContactButton(
                text = "Contacter",
                onClick = onContactClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ProfileHeader(name: String, role: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = name)
        Text(text = role)
    }
}

// compétences : réutilise SkillItem pour chaque compétence
@Composable
fun SkillList(skills: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Compétences :")
        skills.forEach { skill ->
            SkillItem(skill = skill, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun SkillItem(skill: String, modifier: Modifier = Modifier) {
    Text(text = "• $skill", modifier = modifier)
}

// Zone cliquable
@Composable
fun AvailabilityInfo(
    isAvailable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val label = if (isAvailable) "Disponible pour un stage" else "Non disponible"
    Surface(
        color = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
            .semantics { contentDescription = "Voir la disponibilité : $label" }
    ) {
        Text(text = label, modifier = Modifier.padding(12.dp))
    }
}

// Bouton d'action réutilisable
@Composable
fun ContactButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = text)
    }
}

// les previews
@Preview(showBackground = true)
@Composable
fun ProfileAndroidJuniorPreview() {
    ProfilDeveloppeurTheme {
        ProfileScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileFullstackUnavailablePreview() {
    ProfilDeveloppeurTheme {
        ProfileCard(
            name = "Sarah Dupont",
            role = "Développeuse full-stack",
            description = "5 ans d'expérience sur des applications web et mobiles.",
            skills = listOf("React", "Node.js", "PostgreSQL", "Docker"),
            isAvailable = false,
            onContactClick = {},
            onAvailabilityClick = {},
            modifier = Modifier.fillMaxWidth().padding(24.dp)
        )
    }
}