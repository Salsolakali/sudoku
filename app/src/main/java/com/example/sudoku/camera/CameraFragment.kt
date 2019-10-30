package com.example.sudoku.camera

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.sudoku.R

import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.otaliastudios.cameraview.Audio
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraUtils
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.IOError
import com.example.sudoku.algorithm.Board
import com.example.sudoku.algorithm.Solver
import com.otaliastudios.cameraview.Flash
import java.util.*


class CameraFragment : Fragment() {

    var sudokuFinal = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))
    
    var sudoku0 = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))
    var sudoku1 = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))
    var sudoku2 = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))
    var sudoku3 = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))
    var sudoku4 = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))
    var sudoku5 = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))
    var sudoku6 = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))
    var sudoku7 = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))
    var sudoku8 = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))
    var sudoku9 = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,0,0,0,0))

    var allSudokus = arrayOf(sudoku0,sudoku1,sudoku2, sudoku3, sudoku4, sudoku5, sudoku6, sudoku7, sudoku8, sudoku9)
    
    var numberScans = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCameraView()
        initListeners()
        val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, 100)
        } else {
            activity?.requestPermissions(permissions, 100)
        }
    }

    private fun initListeners() {

        camera_view.setOnTouchListener { v, event ->
            val x = event?.x?.toInt()
            val y = event?.y?.toInt()
            Log.e("ALBERTO event: ", "x$x y$y")
        false}
        camera_view.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(jpeg: ByteArray?) {
                //camera_view.stop()
                CameraUtils.decodeBitmap(jpeg) { bitmap ->
                    val image = FirebaseVisionImage.fromBitmap(bitmap)
                    val textRecognizer = FirebaseVision.getInstance()
                        .onDeviceTextRecognizer
                    textRecognizer.processImage(image)
                        .addOnSuccessListener {
                            processTextBlocks(it)
                            /*camera_view.visibility = View.GONE
                            tvCamera.visibility = View.GONE*/
                            /*val intent = Intent(this@ScannerActivity, MainActivity::class.java)
                            intent.putExtra("number", processTextRecognitionResult(it))
                            startActivity(intent)*/
                        }
                        .addOnFailureListener {
                            showToast(it.localizedMessage)
                        }
                    super.onPictureTaken(jpeg)
                }
            }
        })
        takePhoto.setOnClickListener {
            camera_view.capturePicture()
            //camera_view.flash = Flash.TORCH
            tvCamera.visibility = View.VISIBLE
        }
    }

    private fun processTextBlocks(result: FirebaseVisionText) {
        var top: Int
        var left: Int
        var right: Int
        var bottom: Int

        var sudokuToFill = allSudokus[numberScans]
        /*Log.e("Alberto", Board(allSudokus[numberScans]).toString() )*/

        val blocks = result.textBlocks
        top = result.textBlocks[0].boundingBox?.top!!
        left = result.textBlocks[0].boundingBox?.left!!
        right = result.textBlocks[0].boundingBox?.right!!
        bottom = result.textBlocks[0].boundingBox?.top!!
        

        for(i in blocks.indices){
            if(blocks[i].boundingBox?.top!! < top)
                top = blocks[i].boundingBox?.top!!
            if(blocks[i].boundingBox?.left!! < left)
                left = blocks[i].boundingBox?.left!!
            if(blocks[i].boundingBox?.right!! > right)
                right = blocks[i].boundingBox?.right!!
            if(blocks[i].boundingBox?.bottom!! > bottom)
                bottom = blocks[i].boundingBox?.bottom!!
        }
        
        var totalWide = right - left
        val totalHeight = bottom - top
        val cellWide = totalWide/9
        val cellHeight = totalHeight/9

        var horizontalPositions = IntArray(9)

        for(i in 0..8){
           horizontalPositions[i] = left + cellWide*i
        }

        var verticalPositions = IntArray(9)
        for(i in 0..8){
            verticalPositions[i] = top + cellHeight*i
        }

        for (i in blocks.indices) {
            val lines = blocks[i].lines
            for (j in lines.indices) {
                var elements = lines[j].elements
                for (k in elements.indices) {
                    locateNumberInSudoku(elements[k],sudokuToFill, horizontalPositions,verticalPositions, numberScans)
               }
            }
        }
        Log.e("Alberto: ", Board(allSudokus[numberScans]).toString())

        if(numberScans<9){
            camera_view.capturePicture()
            numberScans++
        }
        else {
            camera_view.stop()
            processSudokus(allSudokus)
            if(Solver(sudokuFinal).solve()){
                var bundle = Bundle()
                for(i in sudokuFinal.indices)
                bundle.putIntArray("$i", sudokuFinal[i])
                findNavController().navigate(R.id.solutionFragment, bundle)
            }
            /*Log.e("Alberto:", "Solved: ")
            Log.e("Alberto:", Board(sudokuFinal).toString())*/
        }
    }

    private fun processSudokus(allSudokus: Array<Array<IntArray>>) {
        var auxiliarArray = IntArray(9)

        for(x in 0..8){
            for (y in 0..8){
                for (i in 0..8) {
                    auxiliarArray[i] = allSudokus[i][x][y]
                }

                sudokuFinal[x][y] = mostCommonNumber(auxiliarArray, auxiliarArray.size)
            }

            /*Log.e("Alberto", "sudokuFinal[j][k] ${sudokuFinal[j][k]} \n J: $j \n K: $k")*/
        }

        Log.e("Alberto","Sudoku final escaneado\n" + Board(sudokuFinal).toString())



    }

    private fun mostCommonNumber(arr: IntArray, n: Int): Int{
        // Sort the array
        Arrays.sort(arr)
        var stringArray=""
        for(i in 0 until n){
            stringArray += arr[i]
        }
        Log.e("Alberto", stringArray)

        // find the max frequency using linear
        // traversal
        var max_count = 1
        var res = arr[0]
        var curr_count = 1

        for (i in 1 until n) {
            if (arr[i] === arr[i - 1])
                curr_count++
            else {
                if (curr_count > max_count) {
                    max_count = curr_count
                    res = arr[i - 1]
                }
                curr_count = 1
            }
        }

        // If last element is most frequent
        if (curr_count > max_count) {
            max_count = curr_count
            res = arr[n - 1]
        }

        return res
    }

    private fun locateNumberInSudoku(element: FirebaseVisionText.Element?, sudoku: Array<IntArray>, horizontalPositions: IntArray, verticalPositions: IntArray, numberOfScan: Int) {
        var horizontalPosition = 8
        var verticalPosition = 8
        for (i in 0..7) {
            if(element?.boundingBox?.left!! >= horizontalPositions[i]-30 && element?.boundingBox?.left!! < horizontalPositions[i+1]+5) {
                horizontalPosition = i
            }
        }

        for(j in 0..7) {
            if (element?.boundingBox?.top!! >= verticalPositions[j] && element?.boundingBox?.top!! < verticalPositions[j + 1]){
                verticalPosition = j
        }
        }
        if (element != null) {
            var number = element.text
            if(number == "B") number = "8"
            if(number == "S") number = "5"
            try {
                if(number.length==1)
                sudoku[verticalPosition][horizontalPosition] = Integer.valueOf(number)
                else if(number.length == 2){
                    sudoku[verticalPosition][horizontalPosition] = Integer.valueOf(number.substring(0,1))
                    sudoku[verticalPosition][horizontalPosition+1] = Integer.valueOf(number.substring(1))
                }
            }
            catch (e: Exception){
                Log.e(
                    "Alberto",
                    "Exception: ${e.toString()}"
                )
            }
        }
    }

    private fun initCameraView() {
        camera_view.start()
        camera_view.audio = Audio.OFF
        camera_view.playSounds = false
        camera_view.cropOutput = true
    }

    private fun processTextRecognitionResult(firebaseVisionText: FirebaseVisionText): String{
        val textSplit =  firebaseVisionText.text.split(">0")
        return try {
            textSplit[1].substring(0,5)
        }catch (e: IOError){
            "00000"
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(activity?.applicationContext, message, Toast.LENGTH_SHORT)
            .show()
    }

}

