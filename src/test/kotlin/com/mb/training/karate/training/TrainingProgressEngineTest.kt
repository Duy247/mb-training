package com.mb.training.karate.training

import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingProgram
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.model.TrainingType
import com.mb.training.karate.services.TrainingProgressSnapshot
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TrainingProgressEngineTest {

    @Test
    fun `engine marks step and exercise done based on file and advances to next exercise`() {
        withTempDir { root ->
            val program = buildProgram()
            val engine = TrainingProgressEngine(root, program)
            var snapshot = TrainingProgressSnapshot(currentItemId = "exercise-1", completedIds = emptySet())

            snapshot = engine.sync(snapshot)
            assertEquals(emptySet(), snapshot.completedIds)
            assertEquals("exercise-1", snapshot.currentItemId)

            root.resolve("a.txt").writeText("ready")
            snapshot = engine.sync(snapshot)
            assertTrue(snapshot.completedIds.contains("exercise-1"))
            assertTrue(snapshot.completedStepIds.contains("exercise-1::step-a"))
            assertEquals("exercise-2", snapshot.currentItemId)
        }
    }

    @Test
    fun `engine completes second exercise when folder condition is met`() {
        withTempDir { root ->
            val program = buildProgram()
            val engine = TrainingProgressEngine(root, program)
            var snapshot = TrainingProgressSnapshot(currentItemId = "exercise-1", completedIds = emptySet())

            root.resolve("a.txt").writeText("ready")
            snapshot = engine.sync(snapshot)
            assertEquals("exercise-2", snapshot.currentItemId)

            root.resolve("sandbox").createDirectories()
            snapshot = engine.sync(snapshot)
            assertTrue(snapshot.completedIds.contains("exercise-2"))
            assertTrue(snapshot.completedStepIds.contains("exercise-2::step-b"))
        }
    }

    @Test
    fun `engine marks command step done only after command is recorded as passed`() {
        withTempDir { root ->
            val program = TrainingProgram(
                id = "cmd-program",
                title = "Command Program",
                exercises = listOf(
                    TrainingExercise(
                        id = "exercise-cmd",
                        title = "Exercise Cmd",
                        level = TrainingLevel.BASIC,
                        type = TrainingType.EXERCISE,
                        objective = "Run test command",
                        startWhen = listOf(TrainingCondition.Always),
                        steps = listOf(
                            TrainingStep(
                                id = "step-run-test",
                                title = "Run test",
                                guidance = "Run mvn -q test",
                                activities = listOf(
                                    TrainingActivity.RunTestTask(
                                        commandHint = "mvn -q test",
                                        commandId = "cmd-mvn-test"
                                    )
                                ),
                                doneWhen = listOf(TrainingCondition.CommandPassed("cmd-mvn-test"))
                            )
                        ),
                        expectedOutcome = "Command passed"
                    )
                )
            )
            val engine = TrainingProgressEngine(root, program)

            var snapshot = TrainingProgressSnapshot(
                currentItemId = "exercise-cmd",
                completedIds = emptySet()
            )
            snapshot = engine.sync(snapshot)
            assertTrue(!snapshot.completedStepIds.contains("exercise-cmd::step-run-test"))
            assertTrue(!snapshot.completedIds.contains("exercise-cmd"))

            snapshot = snapshot.copy(passedCommandIds = setOf("cmd-mvn-test"))
            snapshot = engine.sync(snapshot)
            assertTrue(snapshot.completedStepIds.contains("exercise-cmd::step-run-test"))
            assertTrue(snapshot.completedIds.contains("exercise-cmd"))
        }
    }

    private fun buildProgram(): TrainingProgram {
        return TrainingProgram(
            id = "test-program",
            title = "Test Program",
            exercises = listOf(
                TrainingExercise(
                    id = "exercise-1",
                    title = "Exercise 1",
                    level = TrainingLevel.BASIC,
                    type = TrainingType.EXERCISE,
                    objective = "Obj1",
                    startWhen = listOf(TrainingCondition.Always),
                    steps = listOf(
                        TrainingStep(
                            id = "step-a",
                            title = "Step A",
                            guidance = "Create file",
                            activities = listOf(TrainingActivity.CreateFile("a.txt", "ready")),
                            doneWhen = listOf(TrainingCondition.FileExists("a.txt"))
                        )
                    ),
                    expectedOutcome = "Done 1"
                ),
                TrainingExercise(
                    id = "exercise-2",
                    title = "Exercise 2",
                    level = TrainingLevel.BASIC,
                    type = TrainingType.EXERCISE,
                    objective = "Obj2",
                    startWhen = listOf(TrainingCondition.ExerciseCompleted("exercise-1")),
                    steps = listOf(
                        TrainingStep(
                            id = "step-b",
                            title = "Step B",
                            guidance = "Create folder",
                            activities = listOf(TrainingActivity.CreateFolder("sandbox")),
                            doneWhen = listOf(TrainingCondition.FolderExists("sandbox"))
                        )
                    ),
                    expectedOutcome = "Done 2"
                )
            )
        )
    }

    private fun withTempDir(block: (Path) -> Unit) {
        val dir = Files.createTempDirectory("mb-training-engine-test-")
        try {
            dir.createDirectories()
            block(dir)
        } finally {
            deleteDirectory(dir)
        }
    }

    private fun deleteDirectory(dir: Path) {
        Files.walkFileTree(dir, object : SimpleFileVisitor<Path>() {
            override fun visitFile(file: Path, attrs: BasicFileAttributes): java.nio.file.FileVisitResult {
                Files.deleteIfExists(file)
                return java.nio.file.FileVisitResult.CONTINUE
            }

            override fun postVisitDirectory(directory: Path, exc: java.io.IOException?): java.nio.file.FileVisitResult {
                Files.deleteIfExists(directory)
                return java.nio.file.FileVisitResult.CONTINUE
            }
        })
    }
}
