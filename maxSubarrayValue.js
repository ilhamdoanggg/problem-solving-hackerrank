'use strict';

const fs = require('fs');

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', function(inputStdin) {
    inputString += inputStdin;
});

process.stdin.on('end', function() {
    inputString = inputString.split('\n');

    main();
});

function readLine() {
    return inputString[currentLine++];
}



/*
 * Complete the 'maxSubarrayValue' function below.
 *
 * The function is expected to return a LONG_INTEGER.
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

function maxSubarrayValue(arr) {
    // Write your code here
    
    let even = [0];
    let odd = [0];

    for (let i = 0; i < arr.length; i++) {
        if (i % 2 === 0) {
            even.push(even[even.length - 1] + arr[i]);
            odd.push(odd[odd.length - 1]);
        } else {
            even.push(even[even.length - 1]);
            odd.push(odd[odd.length - 1] + arr[i]);
        }
    }

    let result = 0;
    for (let i = 0; i < arr.length; i++) {
        for (let j = i + 1; j <= arr.length; j++) {
            let a = even[j] - even[i];
            let b = odd[j] - odd[i];
            result = Math.max(result, (a - b) * (a - b));
        }
    }
    return result;

}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const arrCount = parseInt(readLine().trim(), 10);

    let arr = [];

    for (let i = 0; i < arrCount; i++) {
        const arrItem = parseInt(readLine().trim(), 10);
        arr.push(arrItem);
    }

    const result = maxSubarrayValue(arr);

    ws.write(result + '\n');

    ws.end();
}
