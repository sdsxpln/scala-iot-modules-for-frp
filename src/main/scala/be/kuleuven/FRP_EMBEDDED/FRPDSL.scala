package be.kuleuven.FRP_EMBEDDED

trait FRPDSL extends EventOps with BehaviorOps

/*
 * Implementation of the FRP DSL
 */
trait FRPDSLImpl extends FRPDSL with EventOpsImpl with BehaviorOpsImpl