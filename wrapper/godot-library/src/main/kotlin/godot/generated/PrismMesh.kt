@file:Suppress("unused", "ClassName", "EnumEntryName", "FunctionName", "SpellCheckingInspection", "PARAMETER_NAME_CHANGED_ON_OVERRIDE", "UnusedImport", "PackageDirectoryMismatch")
package godot

import godot.gdnative.*
import godot.core.*
import godot.utils.*
import godot.icalls.*
import kotlinx.cinterop.*


// NOTE: THIS FILE IS AUTO GENERATED FROM JSON API CONFIG


open class PrismMesh : PrimitiveMesh {
    constructor() : super("PrismMesh")
    constructor(variant: Variant) : super(variant)
    internal constructor(mem: COpaquePointer) : super(mem)
    internal constructor(name: String) : super(name)


    // Enums 



    // Signals
    class Signal {
        companion object {
        }
    }


    companion object {
        infix fun from(other: PrimitiveMesh): PrismMesh = PrismMesh("").apply { setRawMemory(other.rawMemory) }
        infix fun from(other: Mesh): PrismMesh = PrismMesh("").apply { setRawMemory(other.rawMemory) }
        infix fun from(other: Resource): PrismMesh = PrismMesh("").apply { setRawMemory(other.rawMemory) }
        infix fun from(other: Reference): PrismMesh = PrismMesh("").apply { setRawMemory(other.rawMemory) }
        infix fun from(other: Object): PrismMesh = PrismMesh("").apply { setRawMemory(other.rawMemory) }
        infix fun from(other: Variant): PrismMesh = fromVariant(PrismMesh(""), other)


        // Constants


    }


    // Properties
    open var leftToRight: Double
        get() = _icall_Double(getLeftToRightMethodBind, this.rawMemory)
        set(value) = _icall_Unit_Double(setLeftToRightMethodBind, this.rawMemory, value)


    open var size: Vector2
        get() = _icall_Vector2(getSizeMethodBind, this.rawMemory)
        set(value) = _icall_Unit_Vector2(setSizeMethodBind, this.rawMemory, value)
    open fun size(shedule: (Vector2) -> Unit): Vector2 = size.apply {
        shedule(this)
        size = this
    }


    open var subdivideWidth: Long
        get() = _icall_Long(getSubdivideWidthMethodBind, this.rawMemory)
        set(value) = _icall_Unit_Long(setSubdivideWidthMethodBind, this.rawMemory, value)


    open var subdivideHeight: Long
        get() = _icall_Long(getSubdivideHeightMethodBind, this.rawMemory)
        set(value) = _icall_Unit_Long(setSubdivideHeightMethodBind, this.rawMemory, value)


    open var subdivideDepth: Long
        get() = _icall_Long(getSubdivideDepthMethodBind, this.rawMemory)
        set(value) = _icall_Unit_Long(setSubdivideDepthMethodBind, this.rawMemory, value)




    // Methods
    private val setLeftToRightMethodBind: CPointer<godot_method_bind> by lazy { getMB("PrismMesh", "set_left_to_right") }
    open fun setLeftToRight(leftToRight: Double) {
        _icall_Unit_Double(setLeftToRightMethodBind, this.rawMemory, leftToRight)
    }


    private val getLeftToRightMethodBind: CPointer<godot_method_bind> by lazy { getMB("PrismMesh", "get_left_to_right") }
    open fun getLeftToRight(): Double {
        return _icall_Double(getLeftToRightMethodBind, this.rawMemory)
    }


    private val setSizeMethodBind: CPointer<godot_method_bind> by lazy { getMB("PrismMesh", "set_size") }
    open fun setSize(size: Vector3) {
        _icall_Unit_Vector3(setSizeMethodBind, this.rawMemory, size)
    }


    private val getSizeMethodBind: CPointer<godot_method_bind> by lazy { getMB("PrismMesh", "get_size") }
    open fun getSize(): Vector3 {
        return _icall_Vector3(getSizeMethodBind, this.rawMemory)
    }


    private val setSubdivideWidthMethodBind: CPointer<godot_method_bind> by lazy { getMB("PrismMesh", "set_subdivide_width") }
    open fun setSubdivideWidth(segments: Long) {
        _icall_Unit_Long(setSubdivideWidthMethodBind, this.rawMemory, segments)
    }


    private val getSubdivideWidthMethodBind: CPointer<godot_method_bind> by lazy { getMB("PrismMesh", "get_subdivide_width") }
    open fun getSubdivideWidth(): Long {
        return _icall_Long(getSubdivideWidthMethodBind, this.rawMemory)
    }


    private val setSubdivideHeightMethodBind: CPointer<godot_method_bind> by lazy { getMB("PrismMesh", "set_subdivide_height") }
    open fun setSubdivideHeight(segments: Long) {
        _icall_Unit_Long(setSubdivideHeightMethodBind, this.rawMemory, segments)
    }


    private val getSubdivideHeightMethodBind: CPointer<godot_method_bind> by lazy { getMB("PrismMesh", "get_subdivide_height") }
    open fun getSubdivideHeight(): Long {
        return _icall_Long(getSubdivideHeightMethodBind, this.rawMemory)
    }


    private val setSubdivideDepthMethodBind: CPointer<godot_method_bind> by lazy { getMB("PrismMesh", "set_subdivide_depth") }
    open fun setSubdivideDepth(segments: Long) {
        _icall_Unit_Long(setSubdivideDepthMethodBind, this.rawMemory, segments)
    }


    private val getSubdivideDepthMethodBind: CPointer<godot_method_bind> by lazy { getMB("PrismMesh", "get_subdivide_depth") }
    open fun getSubdivideDepth(): Long {
        return _icall_Long(getSubdivideDepthMethodBind, this.rawMemory)
    }


}
