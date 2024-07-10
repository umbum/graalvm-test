package dev.umbum.config

import io.github.classgraph.ClassGraph
import org.springframework.aot.hint.MemberCategory
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.aot.hint.TypeReference
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportRuntimeHints

@Configuration
@ImportRuntimeHints(CooChanManChanHintRegisterConfig.HintRegister::class)
class CooChanManChanHintRegisterConfig {
    class HintRegister : RuntimeHintsRegistrar {
        override fun registerHints(
            hints: RuntimeHints,
            classLoader: ClassLoader?,
        ) {
            val classes = getClassesInNestedPackages("dev.umbum.external.currencybeacon")
            val typeReferences = TypeReference.listOf(*classes.toTypedArray())

            hints
                .reflection()
                .registerTypes(typeReferences) {
                    it.withMembers(
                        MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                        MemberCategory.INVOKE_DECLARED_METHODS,
                        MemberCategory.DECLARED_FIELDS,
                    )
                }
        }

        private fun getClassesInNestedPackages(basePackage: String): List<Class<*>> {
            val scanResult =
                ClassGraph()
                    .acceptPackages(basePackage)
                    .scan()

            return scanResult.allClasses.map { it.loadClass() }
        }
    }
}
