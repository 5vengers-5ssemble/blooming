package com.fivengers.blooming.config.contract;

public class KIP17BloomingToken {

    public static final String ABI_JSON = """
            [
            \t{
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "string",
            \t\t\t\t"name": "name",
            \t\t\t\t"type": "string"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "string",
            \t\t\t\t"name": "symbol",
            \t\t\t\t"type": "string"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "constructor"
            \t},
            \t{
            \t\t"anonymous": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "owner",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "approved",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "Approval",
            \t\t"type": "event"
            \t},
            \t{
            \t\t"anonymous": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "owner",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "operator",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"indexed": false,
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "approved",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"name": "ApprovalForAll",
            \t\t"type": "event"
            \t},
            \t{
            \t\t"anonymous": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "account",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "MinterAdded",
            \t\t"type": "event"
            \t},
            \t{
            \t\t"anonymous": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "account",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "MinterRemoved",
            \t\t"type": "event"
            \t},
            \t{
            \t\t"anonymous": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "previousOwner",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "newOwner",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "OwnershipTransferred",
            \t\t"type": "event"
            \t},
            \t{
            \t\t"anonymous": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"indexed": false,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "account",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "Paused",
            \t\t"type": "event"
            \t},
            \t{
            \t\t"anonymous": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "account",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "PauserAdded",
            \t\t"type": "event"
            \t},
            \t{
            \t\t"anonymous": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "account",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "PauserRemoved",
            \t\t"type": "event"
            \t},
            \t{
            \t\t"anonymous": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "from",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "to",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"indexed": true,
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "Transfer",
            \t\t"type": "event"
            \t},
            \t{
            \t\t"anonymous": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"indexed": false,
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "account",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "Unpaused",
            \t\t"type": "event"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "account",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "addMinter",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "account",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "addPauser",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "user",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "requestedCount",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "airDropMint",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "to",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "approve",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "owner",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "balanceOf",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "burn",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "getApproved",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "owner",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "operator",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "isApprovedForAll",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "account",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "isMinter",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "isOwner",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "account",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "isPauser",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "merkleRoot",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bytes32",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bytes32"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "to",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "mint",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "to",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "string",
            \t\t\t\t"name": "tokenURI",
            \t\t\t\t"type": "string"
            \t\t\t}
            \t\t],
            \t\t"name": "mintWithTokenURI",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "mintingInformation",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256[7]",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "uint256[7]"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "name",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "string",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "string"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "owner",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address payable",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "ownerOf",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [],
            \t\t"name": "pause",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "paused",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "requestedCount",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "publicMint",
            \t\t"outputs": [],
            \t\t"payable": true,
            \t\t"stateMutability": "payable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "publicMintEnabled",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [],
            \t\t"name": "renounceMinter",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [],
            \t\t"name": "renounceOwnership",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [],
            \t\t"name": "renouncePauser",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "_state",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"name": "reveal",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "revealed",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "from",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "to",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "safeTransferFrom",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "from",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "to",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "bytes",
            \t\t\t\t"name": "_data",
            \t\t\t\t"type": "bytes"
            \t\t\t}
            \t\t],
            \t\t"name": "safeTransferFrom",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "to",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "approved",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"name": "setApprovalForAll",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "string",
            \t\t\t\t"name": "_newBaseURI",
            \t\t\t\t"type": "string"
            \t\t\t}
            \t\t],
            \t\t"name": "setBaseURI",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bytes32",
            \t\t\t\t"name": "_merkleRoot",
            \t\t\t\t"type": "bytes32"
            \t\t\t}
            \t\t],
            \t\t"name": "setMerkleRoot",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "string",
            \t\t\t\t"name": "_newNotRevealedURI",
            \t\t\t\t"type": "string"
            \t\t\t}
            \t\t],
            \t\t"name": "setNotRevealedURI",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "_state",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"name": "setPublicMintEnabled",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "_state",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"name": "setWhitelistMintEnabled",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "newAntibotInterval",
            \t\t\t\t"type": "uint256"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "newMintLimitPerBlock",
            \t\t\t\t"type": "uint256"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "newMintLimitPerSale",
            \t\t\t\t"type": "uint256"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "newMintStartBlockNumber",
            \t\t\t\t"type": "uint256"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "newMintIndexForSale",
            \t\t\t\t"type": "uint256"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "newMaxSaleAmount",
            \t\t\t\t"type": "uint256"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "newMintPrice",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "setupSale",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bytes4",
            \t\t\t\t"name": "interfaceId",
            \t\t\t\t"type": "bytes4"
            \t\t\t}
            \t\t],
            \t\t"name": "supportsInterface",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "symbol",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "string",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "string"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "index",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "tokenByIndex",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "owner",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "index",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "tokenOfOwnerByIndex",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "tokenURI",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "string",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "string"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "totalSupply",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "from",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "to",
            \t\t\t\t"type": "address"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "tokenId",
            \t\t\t\t"type": "uint256"
            \t\t\t}
            \t\t],
            \t\t"name": "transferFrom",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address payable",
            \t\t\t\t"name": "newOwner",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "transferOwnership",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [],
            \t\t"name": "unpause",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "address",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "address"
            \t\t\t}
            \t\t],
            \t\t"name": "whitelistClaimed",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [
            \t\t\t{
            \t\t\t\t"internalType": "uint256",
            \t\t\t\t"name": "requestedCount",
            \t\t\t\t"type": "uint256"
            \t\t\t},
            \t\t\t{
            \t\t\t\t"internalType": "bytes32[]",
            \t\t\t\t"name": "_merkleProof",
            \t\t\t\t"type": "bytes32[]"
            \t\t\t}
            \t\t],
            \t\t"name": "whitelistMint",
            \t\t"outputs": [],
            \t\t"payable": true,
            \t\t"stateMutability": "payable",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": true,
            \t\t"inputs": [],
            \t\t"name": "whitelistMintEnabled",
            \t\t"outputs": [
            \t\t\t{
            \t\t\t\t"internalType": "bool",
            \t\t\t\t"name": "",
            \t\t\t\t"type": "bool"
            \t\t\t}
            \t\t],
            \t\t"payable": false,
            \t\t"stateMutability": "view",
            \t\t"type": "function"
            \t},
            \t{
            \t\t"constant": false,
            \t\t"inputs": [],
            \t\t"name": "withdraw",
            \t\t"outputs": [],
            \t\t"payable": false,
            \t\t"stateMutability": "nonpayable",
            \t\t"type": "function"
            \t}
            ]""";
    public static String BYTE_CODE = "60806040526000601960006101000a81548160ff0219169083151502179055506000601960016101000a81548160ff0219169083151502179055506000601c60006101000a81548160ff0219169083151502179055503480156200006257600080fd5b506040516200643d3803806200643d833981810160405260408110156200008857600080fd5b8101908080516040519392919084640100000000821115620000a957600080fd5b83820191506020820185811115620000c057600080fd5b8251866001820283011164010000000082111715620000de57600080fd5b8083526020830192505050908051906020019080838360005b8381101562000114578082015181840152602081019050620000f7565b50505050905090810190601f168015620001425780820380516001836020036101000a031916815260200191505b50604052602001805160405193929190846401000000008211156200016657600080fd5b838201915060208201858111156200017d57600080fd5b82518660018202830111640100000000821117156200019b57600080fd5b8083526020830192505050908051906020019080838360005b83811015620001d1578082015181840152602081019050620001b4565b50505050905090810190601f168015620001ff5780820380516001836020036101000a031916815260200191505b5060405250505081818181620002226301ffc9a760e01b6200040b60201b60201c565b6200023a6380ac58cd60e01b6200040b60201b60201c565b6200025263780e9d6360e01b6200040b60201b60201c565b81600990805190602001906200026a9291906200079a565b5080600a9080519060200190620002839291906200079a565b506200029c635b5e139f60e01b6200040b60201b60201c565b505033600c60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a36200036d336200051460201b60201c565b600160108190555050506200038f63eab83e2060e01b6200040b60201b60201c565b620003a763fac27f4660e01b6200040b60201b60201c565b620003bf6342966c6860e01b6200040b60201b60201c565b620003d0336200057560201b60201c565b6000601e60006101000a81548160ff02191690831515021790555062000403634d5507ff60e01b6200040b60201b60201c565b505062000849565b63ffffffff60e01b817bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161415620004a8576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f4b495031333a20696e76616c696420696e74657266616365206964000000000081525060200191505060405180910390fd5b6001600080837bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b6200052f81600d620005d660201b62004cae1790919060201c565b8073ffffffffffffffffffffffffffffffffffffffff167f6ae172837ea30b801fbfcdd4108aa1d5bf8ff775444fd70256b44e6bf3dfc3f660405160405180910390a250565b6200059081601d620005d660201b62004cae1790919060201c565b8073ffffffffffffffffffffffffffffffffffffffff167f6719d08c1888103bea251a4ed56406bd0c3e69723c8a1686e017e7bbe159b6f860405160405180910390a250565b620005e88282620006ba60201b60201c565b156200065c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f526f6c65733a206163636f756e7420616c72656164792068617320726f6c650081525060200191505060405180910390fd5b60018260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16141562000743576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001806200641b6022913960400191505060405180910390fd5b8260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620007dd57805160ff19168380011785556200080e565b828001600101855582156200080e579182015b828111156200080d578251825591602001919060010190620007f0565b5b5090506200081d919062000821565b5090565b6200084691905b808211156200084257600081600090555060010162000828565b5090565b90565b615bc280620008596000396000f3fe6080604052600436106102ae5760003560e01c80636ef8d66d11610175578063983b2d56116100dc578063b88d4fde11610095578063db4bec441161006f578063db4bec44146112b0578063e985e9c514611319578063f2c4ce1e146113a2578063f2fde38b1461146a576102ae565b8063b88d4fde14611067578063c87b56dd14611179578063d2cab0561461122d576102ae565b8063983b2d5614610e855780639865027514610ed6578063a22cb46514610eed578063a386756114610f4a578063aa271e1a14610fc1578063b767a0981461102a576102ae565b806382dc1ec41161012e57806382dc1ec414610cca5780638456cb5914610d1b5780638da5cb5b14610d325780638f32d59b14610d89578063940cd05b14610db857806395d89b4114610df5576102ae565b80636ef8d66d14610b6457806370a0823114610b7b578063715018a614610be057806375a1ed0814610bf75780637cb6475914610c52578063818668d714610c8d576102ae565b80633f4ba83a1161021957806350bb4e7f116101d257806350bb4e7f1461088a578063518302271461099457806355f804b3146109c35780635c975abb14610a8b5780636352211e14610aba5780636caede3d14610b35576102ae565b80633f4ba83a1461069257806340c10f19146106a957806342842e0e1461071c57806342966c681461079757806346fbf68e146107d25780634f6ccce71461083b576102ae565b80631dd8792b1161026b5780631dd8792b146104e557806323b872dd146105385780632db11544146105b35780632eb4a7ab146105e15780632f745c591461060c5780633ccfd60b1461067b576102ae565b806301ffc9a7146102b357806306fdde0314610325578063081812fc146103b5578063095ea7b3146104305780630f4161aa1461048b57806318160ddd146104ba575b600080fd5b3480156102bf57600080fd5b5061030b600480360360208110156102d657600080fd5b8101908080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191690602001909291905050506114bb565b604051808215151515815260200191505060405180910390f35b34801561033157600080fd5b5061033a611522565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561037a57808201518184015260208101905061035f565b50505050905090810190601f1680156103a75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156103c157600080fd5b506103ee600480360360208110156103d857600080fd5b81019080803590602001909291905050506115c4565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561043c57600080fd5b506104896004803603604081101561045357600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061165f565b005b34801561049757600080fd5b506104a06116f0565b604051808215151515815260200191505060405180910390f35b3480156104c657600080fd5b506104cf611703565b6040518082815260200191505060405180910390f35b3480156104f157600080fd5b506104fa611710565b6040518082600760200280838360005b8381101561052557808201518184015260208101905061050a565b5050505090500191505060405180910390f35b34801561054457600080fd5b506105b16004803603606081101561055b57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061176a565b005b6105df600480360360208110156105c957600080fd5b81019080803590602001909291905050506117fd565b005b3480156105ed57600080fd5b506105f6611c4b565b6040518082815260200191505060405180910390f35b34801561061857600080fd5b506106656004803603604081101561062f57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611c51565b6040518082815260200191505060405180910390f35b34801561068757600080fd5b50610690611d10565b005b34801561069e57600080fd5b506106a7611e1f565b005b3480156106b557600080fd5b50610702600480360360408110156106cc57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611f7f565b604051808215151515815260200191505060405180910390f35b34801561072857600080fd5b506107956004803603606081101561073f57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611ff3565b005b3480156107a357600080fd5b506107d0600480360360208110156107ba57600080fd5b8101908080359060200190929190505050612013565b005b3480156107de57600080fd5b50610821600480360360208110156107f557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061207e565b604051808215151515815260200191505060405180910390f35b34801561084757600080fd5b506108746004803603602081101561085e57600080fd5b810190808035906020019092919050505061209b565b6040518082815260200191505060405180910390f35b34801561089657600080fd5b5061097a600480360360608110156108ad57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001906401000000008111156108f457600080fd5b82018360208201111561090657600080fd5b8035906020019184600183028401116401000000008311171561092857600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050919291929050505061211b565b604051808215151515815260200191505060405180910390f35b3480156109a057600080fd5b506109a961219a565b604051808215151515815260200191505060405180910390f35b3480156109cf57600080fd5b50610a89600480360360208110156109e657600080fd5b8101908080359060200190640100000000811115610a0357600080fd5b820183602082011115610a1557600080fd5b80359060200191846001830284011164010000000083111715610a3757600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506121ad565b005b348015610a9757600080fd5b50610aa0612225565b604051808215151515815260200191505060405180910390f35b348015610ac657600080fd5b50610af360048036036020811015610add57600080fd5b810190808035906020019092919050505061223c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b348015610b4157600080fd5b50610b4a612304565b604051808215151515815260200191505060405180910390f35b348015610b7057600080fd5b50610b79612317565b005b348015610b8757600080fd5b50610bca60048036036020811015610b9e57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050612322565b6040518082815260200191505060405180910390f35b348015610bec57600080fd5b50610bf56123f7565b005b348015610c0357600080fd5b50610c5060048036036040811015610c1a57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050612532565b005b348015610c5e57600080fd5b50610c8b60048036036020811015610c7557600080fd5b810190808035906020019092919050505061264e565b005b348015610c9957600080fd5b50610cc860048036036020811015610cb057600080fd5b810190808035151590602001909291905050506126b6565b005b348015610cd657600080fd5b50610d1960048036036020811015610ced57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050612731565b005b348015610d2757600080fd5b50610d3061279b565b005b348015610d3e57600080fd5b50610d476128fc565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b348015610d9557600080fd5b50610d9e612926565b604051808215151515815260200191505060405180910390f35b348015610dc457600080fd5b50610df360048036036020811015610ddb57600080fd5b8101908080351515906020019092919050505061297e565b005b348015610e0157600080fd5b50610e0a6129f9565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610e4a578082015181840152602081019050610e2f565b50505050905090810190601f168015610e775780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b348015610e9157600080fd5b50610ed460048036036020811015610ea857600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050612a9b565b005b348015610ee257600080fd5b50610eeb612b05565b005b348015610ef957600080fd5b50610f4860048036036040811015610f1057600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803515159060200190929190505050612b10565b005b348015610f5657600080fd5b50610fbf600480360360e0811015610f6d57600080fd5b8101908080359060200190929190803590602001909291908035906020019092919080359060200190929190803590602001909291908035906020019092919080359060200190929190505050612ba1565b005b348015610fcd57600080fd5b5061101060048036036020811015610fe457600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050612c39565b604051808215151515815260200191505060405180910390f35b34801561103657600080fd5b506110656004803603602081101561104d57600080fd5b81019080803515159060200190929190505050612c56565b005b34801561107357600080fd5b506111776004803603608081101561108a57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001906401000000008111156110f157600080fd5b82018360208201111561110357600080fd5b8035906020019184600183028401116401000000008311171561112557600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050612cd1565b005b34801561118557600080fd5b506111b26004803603602081101561119c57600080fd5b8101908080359060200190929190505050612d43565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156111f25780820151818401526020810190506111d7565b50505050905090810190601f16801561121f5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6112ae6004803603604081101561124357600080fd5b81019080803590602001909291908035906020019064010000000081111561126a57600080fd5b82018360208201111561127c57600080fd5b8035906020019184602083028401116401000000008311171561129e57600080fd5b9091929391929390505050613002565b005b3480156112bc57600080fd5b506112ff600480360360208110156112d357600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506133d3565b604051808215151515815260200191505060405180910390f35b34801561132557600080fd5b506113886004803603604081101561133c57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506133f3565b604051808215151515815260200191505060405180910390f35b3480156113ae57600080fd5b50611468600480360360208110156113c557600080fd5b81019080803590602001906401000000008111156113e257600080fd5b8201836020820111156113f457600080fd5b8035906020019184600183028401116401000000008311171561141657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050613487565b005b34801561147657600080fd5b506114b96004803603602081101561148d57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506134ff565b005b6000806000837bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff169050919050565b606060098054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115ba5780601f1061158f576101008083540402835291602001916115ba565b820191906000526020600020905b81548152906001019060200180831161159d57829003601f168201915b5050505050905090565b60006115cf82613585565b611624576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602b815260200180615b01602b913960400191505060405180910390fd5b6002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b601e60009054906101000a900460ff16156116e2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260108152602001807f5061757361626c653a207061757365640000000000000000000000000000000081525060200191505060405180910390fd5b6116ec82826135f7565b5050565b601960019054906101000a900460ff1681565b6000600780549050905090565b61171861569b565b61172061569b565b6040518060e00160405280600f5481526020016010548152602001601154815260200160125481526020016014548152602001601554815260200160165481525090508091505090565b601e60009054906101000a900460ff16156117ed576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260108152602001807f5061757361626c653a207061757365640000000000000000000000000000000081525060200191505060405180910390fd5b6117f88383836137ed565b505050565b601960019054906101000a900460ff1661187f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f546865207075626c69632073616c65206973206e6f7420656e61626c6564210081525060200191505060405180910390fd5b436118d4600f54600e60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461385c90919063ffffffff16565b10611947576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807f426f74206973206e6f7420616c6c6f776564000000000000000000000000000081525060200191505060405180910390fd5b6014544310156119bf576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f4e6f74207965742073746172746564000000000000000000000000000000000081525060200191505060405180910390fd5b6000811180156119d157506011548111155b611a26576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602181526020018061585b6021913960400191505060405180910390fd5b611a3b816016546138e490919063ffffffff16565b3414611aaf576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f4e6f7420656e6f756768204b6c6179000000000000000000000000000000000081525060200191505060405180910390fd5b600160155401611aca8260105461385c90919063ffffffff16565b1115611b3e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f457863656564206d617820616d6f756e7400000000000000000000000000000081525060200191505060405180910390fd5b60125481611b4b33612322565b011115611bc0576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601c8152602001807f457863656564206d617820616d6f756e742070657220706572736f6e0000000081525060200191505060405180910390fd5b60008090505b81811015611c0357611bda3360105461396a565b611bf0600160105461385c90919063ffffffff16565b6010819055508080600101915050611bc6565b5043600e60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555050565b601a5481565b6000611c5c83612322565b8210611cb3576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602a81526020018061589f602a913960400191505060405180910390fd5b600560008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208281548110611cfd57fe5b9060005260206000200154905092915050565b611d1933612c39565b611d6e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b733e944ca8b08a0a0d3245b05abf01586b9142f52c73ffffffffffffffffffffffffffffffffffffffff166108fc60646005470281611da957fe5b049081150290604051600060405180830381858888f19350505050158015611dd5573d6000803e3d6000fd5b503373ffffffffffffffffffffffffffffffffffffffff166108fc479081150290604051600060405180830381858888f19350505050158015611e1c573d6000803e3d6000fd5b50565b611e283361207e565b611e7d576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158056030913960400191505060405180910390fd5b601e60009054906101000a900460ff16611eff576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f5061757361626c653a206e6f742070617573656400000000000000000000000081525060200191505060405180910390fd5b6000601e60006101000a81548160ff0219169083151502179055507f5db9ee0a495bf2e6ff9c91a7834c1ba4fdd244a5e8aa4e537bd38aeae4b073aa33604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a1565b6000611f8a33612c39565b611fdf576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b611fe9838361396a565b6001905092915050565b61200e83838360405180602001604052806000815250612cd1565b505050565b61201d338261398b565b612072576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602f815260200180615a10602f913960400191505060405180910390fd5b61207b81613a7f565b50565b600061209482601d613a9490919063ffffffff16565b9050919050565b60006120a5611703565b82106120fc576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602b815260200180615a90602b913960400191505060405180910390fd5b6007828154811061210957fe5b90600052602060002001549050919050565b600061212633612c39565b61217b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b612185848461396a565b61218f8383613b72565b600190509392505050565b601960009054906101000a900460ff1681565b6121b633612c39565b61220b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b80601790805190602001906122219291906156bd565b5050565b6000601e60009054906101000a900460ff16905090565b6000806001600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156122fb576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602881526020018061591a6028913960400191505060405180910390fd5b80915050919050565b601c60009054906101000a900460ff1681565b61232033613bfc565b565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156123a9576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526029815260200180615a3f6029913960400191505060405180910390fd5b6123f0600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020613c56565b9050919050565b6123ff612926565b612471576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff16600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a36000600c60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550565b61253b33612c39565b612590576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b60008111612606576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600c8152602001807f7a65726f2072657175657374000000000000000000000000000000000000000081525060200191505060405180910390fd5b60008090505b81811015612649576126208360105461396a565b612636600160105461385c90919063ffffffff16565b601081905550808060010191505061260c565b505050565b61265733612c39565b6126ac576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b80601a8190555050565b6126bf33612c39565b612714576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b80601960016101000a81548160ff02191690831515021790555050565b61273a3361207e565b61278f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158056030913960400191505060405180910390fd5b61279881613c64565b50565b6127a43361207e565b6127f9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158056030913960400191505060405180910390fd5b601e60009054906101000a900460ff161561287c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260108152602001807f5061757361626c653a207061757365640000000000000000000000000000000081525060200191505060405180910390fd5b6001601e60006101000a81548160ff0219169083151502179055507f62e78cea01bee320cd4e420270b5ea74000d11b0c9f74754ebdbfc544b05a25833604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a1565b6000600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614905090565b61298733612c39565b6129dc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b80601960006101000a81548160ff02191690831515021790555050565b6060600a8054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015612a915780601f10612a6657610100808354040283529160200191612a91565b820191906000526020600020905b815481529060010190602001808311612a7457829003601f168201915b5050505050905090565b612aa433612c39565b612af9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b612b0281613cbe565b50565b612b0e33613d18565b565b601e60009054906101000a900460ff1615612b93576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260108152602001807f5061757361626c653a207061757365640000000000000000000000000000000081525060200191505060405180910390fd5b612b9d8282613d72565b5050565b612baa33612c39565b612bff576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b86600f8190555085601181905550846012819055508360148190555082601081905550816015819055508060168190555050505050505050565b6000612c4f82600d613a9490919063ffffffff16565b9050919050565b612c5f33612c39565b612cb4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b80601c60006101000a81548160ff02191690831515021790555050565b612cdc84848461176a565b612ce884848484613f15565b612d3d576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806159e06030913960400191505060405180910390fd5b50505050565b6060612d4e82613585565b612da3576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602e8152602001806157d7602e913960400191505060405180910390fd5b60001515601960009054906101000a900460ff1615151415612ee0576060612dc9614477565b90506000815111612de95760405180602001604052806000815250612ed8565b80612df384614519565b6040516020018083805190602001908083835b60208310612e295780518252602082019150602081019050602083039250612e06565b6001836020036101000a03801982511681845116808217855250505050505090500182805190602001908083835b60208310612e7a5780518252602082019150602081019050602083039250612e57565b6001836020036101000a038019825116818451168082178552505050505050905001807f2e6a736f6e000000000000000000000000000000000000000000000000000000815250600501925050506040516020818303038152906040525b915050612ffd565b6060612eea614646565b90506000815111612f0a5760405180602001604052806000815250612ff9565b80612f1484614519565b6040516020018083805190602001908083835b60208310612f4a5780518252602082019150602081019050602083039250612f27565b6001836020036101000a03801982511681845116808217855250505050505090500182805190602001908083835b60208310612f9b5780518252602082019150602081019050602083039250612f78565b6001836020036101000a038019825116818451168082178552505050505050905001807f2e6a736f6e000000000000000000000000000000000000000000000000000000815250600501925050506040516020818303038152906040525b9150505b919050565b601c60009054906101000a900460ff16613067576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526022815260200180615abb6022913960400191505060405180910390fd5b61307c836016546138e490919063ffffffff16565b34146130f0576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f4e6f7420656e6f756768204b6c6179000000000000000000000000000000000081525060200191505060405180910390fd5b601b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16156131b0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260188152602001807f4164647265737320616c726561647920636c61696d656421000000000000000081525060200191505060405180910390fd5b6000831180156131c257506011548311155b613217576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602181526020018061585b6021913960400191505060405180910390fd5b600033604051602001808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1660601b81526014019150506040516020818303038152906040528051906020012090506132bf838380806020026020016040519081016040528093929190818152602001838360200280828437600081840152601f19601f82011690508083019250505050505050601a54836146e8565b613331576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f496e76616c69642070726f6f662100000000000000000000000000000000000081525060200191505060405180910390fd5b60008090505b848110156133745761334b3360105461396a565b613361600160105461385c90919063ffffffff16565b6010819055508080600101915050613337565b506001601b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555050505050565b601b6020528060005260406000206000915054906101000a900460ff1681565b6000600460008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b61349033612c39565b6134e5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260308152602001806158c96030913960400191505060405180910390fd5b80601890805190602001906134fb9291906156bd565b5050565b613507612926565b613579576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657281525060200191505060405180910390fd5b613582816146ff565b50565b6000806001600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415915050919050565b60006136028261223c565b90508073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614156136a6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f4b495031373a20617070726f76616c20746f2063757272656e74206f776e657281525060200191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614806136e657506136e581336133f3565b5b61373b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526037815260200180615b2c6037913960400191505060405180910390fd5b826002600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550818373ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a4505050565b6137f7338261398b565b61384c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603081526020018061598e6030913960400191505060405180910390fd5b613857838383614845565b505050565b6000808284019050838110156138da576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b8091505092915050565b6000808314156138f75760009050613964565b600082840290508284828161390857fe5b041461395f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602181526020018061596d6021913960400191505060405180910390fd5b809150505b92915050565b6139748282614869565b61397e8282614a81565b61398781614b48565b5050565b600061399682613585565b6139eb576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602b815260200180615b63602b913960400191505060405180910390fd5b60006139f68361223c565b90508073ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff161480613a6557508373ffffffffffffffffffffffffffffffffffffffff16613a4d846115c4565b73ffffffffffffffffffffffffffffffffffffffff16145b80613a765750613a7581856133f3565b5b91505092915050565b613a91613a8b8261223c565b82614b94565b50565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415613b1b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001806159be6022913960400191505060405180910390fd5b8260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b613b7b82613585565b613bd0576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602b815260200180615942602b913960400191505060405180910390fd5b80600b60008481526020019081526020016000209080519060200190613bf79291906156bd565b505050565b613c1081601d614bf190919063ffffffff16565b8073ffffffffffffffffffffffffffffffffffffffff167fcd265ebaf09df2871cc7bd4133404a235ba12eff2041bb89d9c714a2621c7c7e60405160405180910390a250565b600081600001549050919050565b613c7881601d614cae90919063ffffffff16565b8073ffffffffffffffffffffffffffffffffffffffff167f6719d08c1888103bea251a4ed56406bd0c3e69723c8a1686e017e7bbe159b6f860405160405180910390a250565b613cd281600d614cae90919063ffffffff16565b8073ffffffffffffffffffffffffffffffffffffffff167f6ae172837ea30b801fbfcdd4108aa1d5bf8ff775444fd70256b44e6bf3dfc3f660405160405180910390a250565b613d2c81600d614bf190919063ffffffff16565b8073ffffffffffffffffffffffffffffffffffffffff167fe94479a9f7e1952cc78f2d6baab678adc1b772d936c6583def489e524cb6669260405160405180910390a250565b3373ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415613e14576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260188152602001807f4b495031373a20617070726f766520746f2063616c6c6572000000000000000081525060200191505060405180910390fd5b80600460003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508173ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c3183604051808215151515815260200191505060405180910390a35050565b6000806060613f398673ffffffffffffffffffffffffffffffffffffffff16614d89565b613f485760019250505061446f565b8573ffffffffffffffffffffffffffffffffffffffff1663150b7a0260e01b33898888604051602401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b83811015614018578082015181840152602081019050613ffd565b50505050905090810190601f1680156140455780820380516001836020036101000a031916815260200191505b5095505050505050604051602081830303815290604052907bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19166020820180517bffffffffffffffffffffffffffffffffffffffffffffffffffffffff83818316178352505050506040518082805190602001908083835b602083106140dd57805182526020820191506020810190506020830392506140ba565b6001836020036101000a0380198251168184511680821785525050505050509050019150506000604051808303816000865af19150503d806000811461413f576040519150601f19603f3d011682016040523d82523d6000602084013e614144565b606091505b50809250819350505060008151141580156141c8575063150b7a0260e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191681806020019051602081101561419657600080fd5b81019080805190602001909291905050507bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916145b156141d85760019250505061446f565b8573ffffffffffffffffffffffffffffffffffffffff16636745782b60e01b33898888604051602401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b838110156142a857808201518184015260208101905061428d565b50505050905090810190601f1680156142d55780820380516001836020036101000a031916815260200191505b5095505050505050604051602081830303815290604052907bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19166020820180517bffffffffffffffffffffffffffffffffffffffffffffffffffffffff83818316178352505050506040518082805190602001908083835b6020831061436d578051825260208201915060208101905060208303925061434a565b6001836020036101000a0380198251168184511680821785525050505050509050019150506000604051808303816000865af19150503d80600081146143cf576040519150601f19603f3d011682016040523d82523d6000602084013e6143d4565b606091505b50809250819350505060008151141580156144585750636745782b60e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191681806020019051602081101561442657600080fd5b81019080805190602001909291905050507bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916145b156144685760019250505061446f565b6000925050505b949350505050565b606060188054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561450f5780601f106144e45761010080835404028352916020019161450f565b820191906000526020600020905b8154815290600101906020018083116144f257829003601f168201915b5050505050905090565b60606000821415614561576040518060400160405280600181526020017f30000000000000000000000000000000000000000000000000000000000000008152509050614641565b600082905060005b6000821461458b578080600101915050600a828161458357fe5b049150614569565b6060816040519080825280601f01601f1916602001820160405280156145c05781602001600182028038833980820191505090505b50905060006001830390505b6000861461463957600a86816145de57fe5b0660300160f81b828280600190039350815181106145f857fe5b60200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600a868161463157fe5b0495506145cc565b819450505050505b919050565b606060178054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156146de5780601f106146b3576101008083540402835291602001916146de565b820191906000526020600020905b8154815290600101906020018083116146c157829003601f168201915b5050505050905090565b6000826146f58584614d9c565b1490509392505050565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415614785576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001806158356026913960400191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff16600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a380600c60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b614850838383614e07565b61485a8382615062565b6148648282614a81565b505050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16141561490c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f4b495031373a206d696e7420746f20746865207a65726f20616464726573730081525060200191505060405180910390fd5b61491581613585565b15614988576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f4b495031373a20746f6b656e20616c7265616479206d696e746564000000000081525060200191505060405180910390fd5b816001600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550614a21600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020615200565b808273ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a45050565b600560008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805490506006600083815260200190815260200160002081905550600560008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190806001815401808255809150509060018203906000526020600020016000909192909190915055505050565b6007805490506008600083815260200190815260200160002081905550600781908060018154018082558091505090600182039060005260206000200160009091929091909150555050565b614b9e8282615216565b6000600b600083815260200190815260200160002080546001816001161561010002031660029004905014614bed57600b60008281526020019081526020016000206000614bec919061573d565b5b5050565b614bfb8282613a94565b614c50576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260218152602001806158f96021913960400191505060405180910390fd5b60008260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b614cb88282613a94565b15614d2b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f526f6c65733a206163636f756e7420616c72656164792068617320726f6c650081525060200191505060405180910390fd5b60018260000160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b600080823b905060008111915050919050565b60008082905060008090505b8451811015614dfc576000858281518110614dbf57fe5b60200260200101519050808311614de157614dda8382615250565b9250614dee565b614deb8184615250565b92505b508080600101915050614da8565b508091505092915050565b8273ffffffffffffffffffffffffffffffffffffffff16614e278261223c565b73ffffffffffffffffffffffffffffffffffffffff1614614e93576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526028815260200180615a686028913960400191505060405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415614f19576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602381526020018061587c6023913960400191505060405180910390fd5b614f2281615267565b614f69600360008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020615325565b614fb0600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020615200565b816001600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550808273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a4505050565b60006150ba6001600560008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208054905061534890919063ffffffff16565b90506000600660008481526020019081526020016000205490508181146151a7576000600560008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020838154811061512757fe5b9060005260206000200154905080600560008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020838154811061517f57fe5b9060005260206000200181905550816006600083815260200190815260200160002081905550505b600560008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208054809190600190036151f99190615785565b5050505050565b6001816000016000828254019250508190555050565b6152208282615392565b61522a8282615062565b6000600660008381526020019081526020016000208190555061524c81615521565b5050565b600082600052816020526040600020905092915050565b600073ffffffffffffffffffffffffffffffffffffffff166002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146153225760006002600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b61533d6001826000015461534890919063ffffffff16565b816000018190555050565b600061538a83836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f7700008152506155db565b905092915050565b8173ffffffffffffffffffffffffffffffffffffffff166153b28261223c565b73ffffffffffffffffffffffffffffffffffffffff161461541e576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526024815260200180615add6024913960400191505060405180910390fd5b61542781615267565b61546e600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020615325565b60006001600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a45050565b600061553c600160078054905061534890919063ffffffff16565b905060006008600084815260200190815260200160002054905060006007838154811061556557fe5b90600052602060002001549050806007838154811061558057fe5b906000526020600020018190555081600860008381526020019081526020016000208190555060078054809190600190036155bb9190615785565b506000600860008681526020019081526020016000208190555050505050565b6000838311158290615688576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825283818151815260200191508051906020019080838360005b8381101561564d578082015181840152602081019050615632565b50505050905090810190601f16801561567a5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b5060008385039050809150509392505050565b6040518060e00160405280600790602082028038833980820191505090505090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106156fe57805160ff191683800117855561572c565b8280016001018555821561572c579182015b8281111561572b578251825591602001919060010190615710565b5b50905061573991906157b1565b5090565b50805460018160011615610100020316600290046000825580601f106157635750615782565b601f01602090049060005260206000209081019061578191906157b1565b5b50565b8154818355818111156157ac578183600052602060002091820191016157ab91906157b1565b5b505050565b6157d391905b808211156157cf5760008160009055506001016157b7565b5090565b9056fe4b495031374d657461646174613a2055524920717565727920666f72206e6f6e6578697374656e7420746f6b656e506175736572526f6c653a2063616c6c657220646f6573206e6f742068617665207468652050617573657220726f6c654f776e61626c653a206e6577206f776e657220697320746865207a65726f2061646472657373546f6f206d616e79207265717565737473206f72207a65726f20726571756573744b495031373a207472616e7366657220746f20746865207a65726f20616464726573734b49503137456e756d657261626c653a206f776e657220696e646578206f7574206f6620626f756e64734d696e746572526f6c653a2063616c6c657220646f6573206e6f74206861766520746865204d696e74657220726f6c65526f6c65733a206163636f756e7420646f6573206e6f74206861766520726f6c654b495031373a206f776e657220717565727920666f72206e6f6e6578697374656e7420746f6b656e4b495031374d657461646174613a2055524920736574206f66206e6f6e6578697374656e7420746f6b656e536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f774b495031373a207472616e736665722063616c6c6572206973206e6f74206f776e6572206e6f7220617070726f766564526f6c65733a206163636f756e7420697320746865207a65726f20616464726573734b495031373a207472616e7366657220746f206e6f6e204b49503137526563656976657220696d706c656d656e7465724b495031374275726e61626c653a2063616c6c6572206973206e6f74206f776e6572206e6f7220617070726f7665644b495031373a2062616c616e636520717565727920666f7220746865207a65726f20616464726573734b495031373a207472616e73666572206f6620746f6b656e2074686174206973206e6f74206f776e4b49503137456e756d657261626c653a20676c6f62616c20696e646578206f7574206f6620626f756e64735468652077686974656c6973742073616c65206973206e6f7420656e61626c6564214b495031373a206275726e206f6620746f6b656e2074686174206973206e6f74206f776e4b495031373a20617070726f76656420717565727920666f72206e6f6e6578697374656e7420746f6b656e4b495031373a20617070726f76652063616c6c6572206973206e6f74206f776e6572206e6f7220617070726f76656420666f7220616c6c4b495031373a206f70657261746f7220717565727920666f72206e6f6e6578697374656e7420746f6b656ea265627a7a72315820216f172bc181246180d77d0fc3ebfd21555df9907c0a12130dc34fa62565b0c364736f6c63430005110032526f6c65733a206163636f756e7420697320746865207a65726f2061646472657373";
}
