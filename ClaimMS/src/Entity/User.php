<?php

namespace App\Entity;

use ApiPlatform\Metadata\ApiResource;
use App\Repository\ClaimRepository;
use DateTimeInterface;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

class User {
    private string $matricule;
    private string $firstName;
    private string $lastName;
    private string $address;
    private string $position;
    private DateTimeInterface $birthDate;
    private DateTimeInterface $startDate;
    private float $leaveBalance;
    private $role;
    private string $phoneNumber;
    private string $phoneSecondary;
    private string $email;
    private string $password;
    private bool $isArchive = false;
    private DateTimeInterface $archiveDate;
    /*private int $children;
    private $leaves = array();
    private $teamUser;
    private $teamUserID;
    private $claim = array();
    private $team;
    private $teamIdT;*/

    public function __construct() {
        // You can initialize your variables here
    }

    public function getMatricule(): string
    {
        return $this->matricule;
    }

    public function setMatricule(string $matricule): void
    {
        $this->matricule = $matricule;
    }

    public function getFirstName(): string
    {
        return $this->firstName;
    }

    public function setFirstName(string $firstName): void
    {
        $this->firstName = $firstName;
    }

    public function getLastName(): string
    {
        return $this->lastName;
    }

    public function setLastName(string $lastName): void
    {
        $this->lastName = $lastName;
    }

    public function getAddress(): string
    {
        return $this->address;
    }

    public function setAddress(string $address): void
    {
        $this->address = $address;
    }

    public function getPosition(): string
    {
        return $this->position;
    }

    public function setPosition(string $position): void
    {
        $this->position = $position;
    }

    public function getBirthDate(): DateTimeInterface
    {
        return $this->birthDate;
    }

    public function setBirthDate(DateTimeInterface $birthDate): void
    {
        $this->birthDate = $birthDate;
    }

    public function getStartDate(): DateTimeInterface
    {
        return $this->startDate;
    }

    public function setStartDate(DateTimeInterface $startDate): void
    {
        $this->startDate = $startDate;
    }

    public function getLeaveBalance(): float
    {
        return $this->leaveBalance;
    }

    public function setLeaveBalance(float $leaveBalance): void
    {
        $this->leaveBalance = $leaveBalance;
    }

    /**
     * @return mixed
     */
    public function getRole()
    {
        return $this->role;
    }

    /**
     * @param mixed $role
     */
    public function setRole($role): void
    {
        $this->role = $role;
    }

    public function getPhoneNumber(): string
    {
        return $this->phoneNumber;
    }

    public function setPhoneNumber(string $phoneNumber): void
    {
        $this->phoneNumber = $phoneNumber;
    }

    public function getPhoneSecondary(): string
    {
        return $this->phoneSecondary;
    }

    public function setPhoneSecondary(string $phoneSecondary): void
    {
        $this->phoneSecondary = $phoneSecondary;
    }

    public function getEmail(): string
    {
        return $this->email;
    }

    public function setEmail(string $email): void
    {
        $this->email = $email;
    }

    public function getPassword(): string
    {
        return $this->password;
    }

    public function setPassword(string $password): void
    {
        $this->password = $password;
    }

    public function isArchive(): bool
    {
        return $this->isArchive;
    }

    public function setIsArchive(bool $isArchive): void
    {
        $this->isArchive = $isArchive;
    }

    public function getArchiveDate(): DateTimeInterface
    {
        return $this->archiveDate;
    }

    public function setArchiveDate(DateTimeInterface $archiveDate): void
    {
        $this->archiveDate = $archiveDate;
    }

}



