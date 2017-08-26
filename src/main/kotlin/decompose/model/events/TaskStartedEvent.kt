package decompose.model.events

import decompose.model.steps.BuildImageStep
import decompose.model.steps.CreateTaskNetworkStep

object TaskStartedEvent : TaskEvent() {
    override fun apply(context: TaskEventContext) {
        context.queueStep(CreateTaskNetworkStep)

        context.allContainers.forEach { context.queueStep(BuildImageStep(it)) }
    }

    override fun toString() = this::class.simpleName!!
}
